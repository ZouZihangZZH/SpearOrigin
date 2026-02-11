package com.zzh.spear_origin.block.entity;

import com.zzh.spear_origin.item.custom.SpearItem; // 👈 记得导入这个！
import com.zzh.spear_origin.screen.SpearReforgingScreenHandler;
import com.zzh.spear_origin.util.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

//BlockEntity：带数据的方块
//ExtendedScreenHandlerFactory：运行方块打开GUI
//ImplementedInventory：通用工具接口
public class SpearReforgingBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    //物品库存：输入武器、模版、材料、输出
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    //数据同步委托
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    //定义构造函数
    public SpearReforgingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPEAR_REFORGING_TABLE, pos, state);//调用父类BlockEntity进行构造
        //初始化数据同步
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> SpearReforgingBlockEntity.this.progress;
                    case 1 -> SpearReforgingBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            public void set(int index, int value) {
                if (index == 0) SpearReforgingBlockEntity.this.progress = value;
                if (index == 1) SpearReforgingBlockEntity.this.maxProgress = value;
            }
            public int size() { return 2; }
        };
    }

    //Tick 逻辑
    public static void tick(World world, BlockPos pos, BlockState state, SpearReforgingBlockEntity entity) {
        //客户端屏蔽
        if(world.isClient()) return;

        // 这里有个小优化：先判断输出槽能不能放，再去检查配方，节省性能
        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, pos, state);

            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, pos, state);
        }
    }

    //检查配方
    private static boolean hasRecipe(SpearReforgingBlockEntity entity) {
        //创建快照：临时库存
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        //查询配方管理器
        Optional<SmithingRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(RecipeType.SMITHING, inventory, entity.getWorld());
        //未查询到返回为空
        if (match.isEmpty()) return false;

        //获取合成结果 (预览)
        ItemStack resultStack = match.get().getOutput(entity.getWorld().getRegistryManager());
        if (resultStack.isEmpty()) return false;

        //转数检查
        if (resultStack.getItem() instanceof SpearItem nextSpear) {
            ItemStack currentWeapon = entity.getStack(1); // 输入槽的武器

            // 获取当前 NBT 转数
            int currentTurn = 0;
            if (currentWeapon.hasNbt() && currentWeapon.getNbt().contains("Turn")) {
                currentTurn = currentWeapon.getNbt().getInt("Turn");
            }

            // 获取下一阶武器需要的转数
            int requiredTurn = nextSpear.getRequiredTurn();

            // 如果当前转数 < 目标武器的要求，禁止合成
            if (currentTurn < requiredTurn) {
                return false;
            }
        }

        //检查输出槽
        return canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, resultStack);
    }

    //执行合成
    private static void craftItem(SpearReforgingBlockEntity entity) {
        //建立临时库存
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        //再次确认配方
        Optional<SmithingRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(RecipeType.SMITHING, inventory, entity.getWorld());

        if(match.isPresent()) {
            //NBT 继承
            ItemStack result = match.get().craft(inventory, entity.getWorld().getRegistryManager());

            // 消耗材料
            entity.removeStack(0, 1);
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);

            // 将保留了 NBT 的结果放入输出槽
            entity.setStack(3, result);

            entity.resetProgress();
        }
    }

    //重置进度
    private void resetProgress() {
        this.progress = 0;
    }

    //辅助判断
    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    //堆叠判断
    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }

    //存盘
    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("spear_reforging.progress", progress);
    }

    //读盘
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("spear_reforging.progress");
    }

    //GUI 相关
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.spear_origin.spear_reforging");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SpearReforgingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
}