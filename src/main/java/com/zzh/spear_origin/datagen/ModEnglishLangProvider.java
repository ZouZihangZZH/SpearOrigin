package com.zzh.spear_origin.datagen;

import com.zzh.spear_origin.block.ModBlocks;
import com.zzh.spear_origin.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModEnglishLangProvider extends FabricLanguageProvider {
    public ModEnglishLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        //翻译物品
        translationBuilder.add(ModItems.WOOD_SPEAR, "Wood Spear");
        translationBuilder.add(ModItems.STONE_SPEAR, "Stone Spear");
        translationBuilder.add(ModItems.COPPER_SPEAR, "Copper Spear");
        translationBuilder.add(ModItems.IRON_SPEAR, "Iron Spear");
        translationBuilder.add(ModItems.GOLD_SPEAR, "Gold Spear");
        translationBuilder.add(ModItems.DIAMOND_SPEAR, "Diamond Spear");
        translationBuilder.add(ModItems.NETHERITE_SPEAR, "Netherite Spear");

        //翻译归元台
        translationBuilder.add(ModBlocks.SPEAR_REFORGING_TABLE,"Origin Pedestal");

        //翻译物品组
        translationBuilder.add("itemGroup.spear_origin", "Spear: Origin");

        //翻译模版
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_wood_spear","Applies to Wood Spear");

    }
}
