package com.zzh.spear_origin.block.entity;

import com.zzh.spear_origin.item.ModItems;
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

public class SpearReforgingBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    // Index 0: 模版, 1: 武器, 2: 材料, 3: 输出
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public SpearReforgingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPEAR_REFORGING_TABLE, pos, state);

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

    // --- ⚙️ Tick 逻辑 ---
    public static void tick(World world, BlockPos pos, BlockState state, SpearReforgingBlockEntity entity) {
        if(world.isClient()) return;

        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, pos, state);

            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
                entity.resetProgress();
            }
        } else {
            entity.resetProgress();
            markDirty(world, pos, state);
        }
    }

    // --- 🔍 检查配方 (包含 NBT 转数检查) ---
    private static boolean hasRecipe(SpearReforgingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        // 1. 基础配方匹配 (查询 JSON)
        Optional<SmithingRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(RecipeType.SMITHING, inventory, entity.getWorld());

        if (match.isEmpty()) return false;

        // 2. --- 🌟 特殊逻辑：检查“转数” ---
        ItemStack weapon = entity.getStack(1); // 获取武器槽

        // 如果是木枪，检查是否达到 9 转
        if (weapon.getItem() == ModItems.WOOD_SPEAR) { // 注意：请确保这里是 WOODEN_SPEAR 还是 WOOD_SPEAR，与你 Item 类一致
            int currentTurn = 0;
            if (weapon.hasNbt() && weapon.getNbt().contains("Turn")) {
                currentTurn = weapon.getNbt().getInt("Turn");
            }

            if (currentTurn < 9) {
                return false; // 转数不够，无法升级
            }
        }

        // 3. 检查输出槽是否有空间
        return canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().craft(inventory, entity.getWorld().getRegistryManager()));
    }

    // --- 🔨 执行合成 ---
    private static void craftItem(SpearReforgingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<SmithingRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(RecipeType.SMITHING, inventory, entity.getWorld());

        if(match.isPresent()) {
            ItemStack result = match.get().craft(inventory, entity.getWorld().getRegistryManager());

            // 消耗原材料
            entity.removeStack(0, 1);
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);

            // 生成新武器
            entity.setStack(3, new ItemStack(result.getItem(),
                    entity.getStack(3).getCount() + result.getCount()));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    // --- 辅助判断 ---
    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }

    // --- 💾 存盘/读盘 ---
    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("spear_reforging.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("spear_reforging.progress");
    }

    // --- 🖥️ GUI 相关 ---
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