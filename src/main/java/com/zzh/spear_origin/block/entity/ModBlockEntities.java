package com.zzh.spear_origin.block.entity;

import com.zzh.spear_origin.SpearOrigin;
import com.zzh.spear_origin.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static BlockEntityType<SpearReforgingBlockEntity> SPEAR_REFORGING_TABLE;

    public static void registerBlockEntities() {
        // 🔍 调试信息 1
        System.out.println("DEBUG: 正在注册 BlockEntities...");

        if (ModBlocks.SPEAR_REFORGING_TABLE == null) {
            // ❌ 重点看这句有没有出来！
            System.out.println("FATAL ERROR: ModBlocks.SPEAR_REFORGING_TABLE 是 NULL！顺序错了！");
        } else {
            System.out.println("DEBUG: 方块已找到，准备绑定...");
        }

        SPEAR_REFORGING_TABLE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(SpearOrigin.MOD_ID, "spear_reforging_table"),
                FabricBlockEntityTypeBuilder.create(SpearReforgingBlockEntity::new,
                        ModBlocks.SPEAR_REFORGING_TABLE).build()
        );

        System.out.println("DEBUG: BlockEntities 注册完成！");
    }
}