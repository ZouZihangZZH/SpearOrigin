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
        SPEAR_REFORGING_TABLE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(SpearOrigin.MOD_ID, "spear_reforging_table"),
                FabricBlockEntityTypeBuilder.create(SpearReforgingBlockEntity::new,
                        ModBlocks.SPEAR_REFORGING_TABLE).build()
        );
    }
}