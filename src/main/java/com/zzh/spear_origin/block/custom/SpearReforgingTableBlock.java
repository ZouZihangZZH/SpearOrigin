package com.zzh.spear_origin.block.custom;

import com.zzh.spear_origin.block.entity.SpearReforgingBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

//继承Block基本属性 会提供实体
public class SpearReforgingTableBlock extends Block implements BlockEntityProvider {

    public SpearReforgingTableBlock(Settings settings) {
        super(settings);
    }

    /**
     * 渲染类型
     * 像普通方块有实体
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /**
     * 创建方块实体
     * 放置时会调用这个方法
     */
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SpearReforgingBlockEntity(pos, state);
    }

    /**
     * 右键交互
     * 当玩家右键点击方块时触发
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            // 获取方块里的“大脑”
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                // 打开 GUI
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    /**
     * 破坏方块
     * 当方块被破坏时，把背包里的东西洒在地上
     */
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SpearReforgingBlockEntity) {
                // ItemScatterer 是个好东西，自动帮你把 Inventory 里的东西扔出来
                ItemScatterer.spawn(world, pos, (SpearReforgingBlockEntity)blockEntity);
                // 通知周围的红石元件更新（比如比较器）
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
}
