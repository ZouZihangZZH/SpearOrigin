package com.zzh.spear_origin.block.entity;

import com.zzh.spear_origin.screen.SpearReforgingScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import com.zzh.spear_origin.util.ImplementedInventory;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

// 1. 实现 ExtendedScreenHandlerFactory：为了能打开带有数据的 GUI
// 2. 实现 ImplementedInventory：为了能存东西
public class SpearReforgingBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    // 定义背包：大小为 3
    // 0: 模版, 1: 武器, 2: 材料
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    public SpearReforgingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPEAR_REFORGING_TABLE, pos, state);
    }

    // --- 💾 存盘与读盘 (NBT) ---

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        // 保存库存到 NBT
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        // 从 NBT 读取库存
        inventory.clear(); // 读取前先清空，防止数据重叠
        Inventories.readNbt(nbt, inventory);
    }

    // --- 📦 库存接口实现 ---

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    // --- 🖥️ GUI 菜单相关 ---

    @Override
    public Text getDisplayName() {
        // 记得去语言文件里加这个翻译键
        return Text.translatable("container.spear_origin.spear_reforging");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        // 🔴 报错预警 2：ScreenHandler 还没写，暂时返回 null 或者先注释掉
         return new SpearReforgingScreenHandler(syncId, playerInventory, this);
    }

    // 把方块的位置信息发给客户端（这样客户端打开 GUI 时知道自己在操作哪个方块）
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
}
