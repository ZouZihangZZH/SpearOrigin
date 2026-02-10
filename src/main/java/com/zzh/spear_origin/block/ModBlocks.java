package com.zzh.spear_origin.block;

import com.zzh.spear_origin.SpearOrigin;
import com.zzh.spear_origin.block.custom.SpearReforgingTableBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    //注册重铸台
    //复制了铁块的属性
    public static final Block SPEAR_REFORGING_TABLE = registerBlock("spear_reforging_table",
            new SpearReforgingTableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(4.0f)));

    /**
     * 辅助注册
     */
    //注册方块
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(SpearOrigin.MOD_ID, name), block);
    }

    // 注册物品
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(SpearOrigin.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    // 初始化方法
    public static void registerModBlocks() {
    }
}