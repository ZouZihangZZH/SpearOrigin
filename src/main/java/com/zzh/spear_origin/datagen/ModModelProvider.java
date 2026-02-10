package com.zzh.spear_origin.datagen;

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
        itemModelGenerator.register(ModItems.WOOD_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLD_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_SPEAR, Models.HANDHELD);
    }
}
