package com.zzh.spear_origin.datagen;

import com.zzh.spear_origin.block.ModBlocks;
import com.zzh.spear_origin.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    //重写方块
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    //重写物品
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //武器模型
        itemModelGenerator.register(ModItems.WOOD_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLD_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_SPEAR, Models.HANDHELD);

        //归元台模型
        itemModelGenerator.register(ModBlocks.SPEAR_REFORGING_TABLE.asItem(), Models.GENERATED);

        //模版模型
        itemModelGenerator.register(ModItems.WOOD_TO_STONE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STONE_TO_COPPER_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_TO_IRON_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_TO_GOLD_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_TO_DIAMOND_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_TO_NETHERITE_TEMPLATE, Models.GENERATED);
    }
}
