package com.zzh.spear_origin.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class SpearReforgingScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    // 🏗️ 客户端构造函数
    // 当客户端收到服务器发来的“打开界面”数据包时调用
    public SpearReforgingScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        // 客户端不知道具体的 Inventory 是啥，所以创建一个 3 格大小的“假”背包
        this(syncId, playerInventory, new SimpleInventory(3));
    }

    // 🏗️ 服务器构造函数
    // 当方块实体在服务器端打开界面时调用
    public SpearReforgingScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        // 🔴 预警：ModScreenHandlers 还没写，等下会报错
        super(ModScreenHandlers.SPEAR_REFORGING_SCREEN_HANDLER, syncId);

        checkSize(inventory, 3); // 检查背包大小是否正确
        this.inventory = inventory;

        // 必须调用，让库存知道被打开了
        inventory.onOpen(playerInventory.player);

        // --- 1. 添加机器自带的 3 个格子 ---
        // 参数：inventory, slotIndex, xPosition, yPosition
        // 这里的坐标 (x, y) 是相对于 GUI 左上角的像素位置
        // 我们暂时假设它们排成一排，以后有了贴图再微调
        this.addSlot(new Slot(inventory, 0, 44, 20)); // 模版槽
        this.addSlot(new Slot(inventory, 1, 80, 20)); // 武器槽
        this.addSlot(new Slot(inventory, 2, 116, 20)); // 材料槽

        // --- 2. 添加玩家背包 (27个格子) ---
        // 这是一个标准的双层循环，用来生成 3x9 的玩家背包区域
        for (int m = 0; m < 3; ++m) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }

        // --- 3. 添加玩家快捷栏 (9个格子) ---
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    // 🔒 权限检查：玩家能不能用这个界面？
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // 🚀 Shift 键快速移动逻辑 (最复杂但必须写的部分)
    // 如果不写这个，玩家按 Shift 拿东西时游戏会崩溃或卡死
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            // 如果点击的是我们机器里的格子 (0, 1, 2)
            if (invSlot < this.inventory.size()) {
                // 尝试移动到玩家背包
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            // 如果点击的是玩家背包里的东西
            else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }
}
