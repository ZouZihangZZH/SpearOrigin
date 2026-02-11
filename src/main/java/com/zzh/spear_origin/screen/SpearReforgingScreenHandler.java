package com.zzh.spear_origin.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class SpearReforgingScreenHandler extends ScreenHandler {
    //定义归元台中物品
    private final Inventory inventory;
    //定义数据同步
    private final PropertyDelegate propertyDelegate;

    // 客户端构造函数
    // 客户端不知道具体数据，所以创建一个假的 ArrayPropertyDelegate (2个数据：进度, 总工时)
    public SpearReforgingScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, new SimpleInventory(4), new ArrayPropertyDelegate(2));
    }

    // 服务器构造函数
    // 这是主构造函数，所有的逻辑都在这里
    public SpearReforgingScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate delegate) {
        super(ModScreenHandlers.SPEAR_REFORGING_SCREEN_HANDLER, syncId);

        // 检查背包大小 (现在是 4 个格子)
        checkSize(inventory, 4);

        this.inventory = inventory;
        this.propertyDelegate = delegate; // 赋值

        inventory.onOpen(playerInventory.player);

        // 添加数据同步
        // 这行代码让客户端能实时看到服务器的进度条变化
        addProperties(delegate);

        // 归元台自带格子 (Input)
        this.addSlot(new Slot(inventory, 0, 44, 20)); // 模版
        this.addSlot(new Slot(inventory, 1, 80, 20)); // 武器
        this.addSlot(new Slot(inventory, 2, 116, 20)); // 材料

        // 归元台输出格子 (Output)
        // 自定义匿名内部类：禁止玩家手动往里塞东西
        this.addSlot(new Slot(inventory, 3, 152, 20) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        // 玩家背包 (3x9)
        for (int m = 0; m < 3; ++m) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }

        // 玩家快捷栏 (1x9)
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    // 辅助方法：获取进度条数据 (供 GUI 渲染使用)
    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 26; // 箭头像素宽度 (根据你的贴图调整)

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift 键快速移动逻辑
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            // 0-3 是机器格子，4-39 是玩家背包
            if (invSlot < 4) {
                // 从机器移到玩家背包
                if (!this.insertItem(originalStack, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(originalStack, newStack);
            } else {
                // 从玩家背包移到机器
                // 优先尝试放入 模版(0) 或 武器(1) 或 材料(2)
                // insertItem 参数：(stack, startIndex, endIndex, fromLast)
                if (!this.insertItem(originalStack, 0, 3, false)) {
                    // 如果机器满了，就什么都不做 (不往输出槽 3 塞东西)
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (originalStack.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, originalStack);
        }
        return newStack;
    }
}