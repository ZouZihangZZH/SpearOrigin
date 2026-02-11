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
        translationBuilder.add(ModItems.WOOD_TO_STONE_TEMPLATE,"Rock Solid Reforging");
        translationBuilder.add(ModItems.STONE_TO_COPPER_TEMPLATE,"Copper Smelting");
        translationBuilder.add(ModItems.COPPER_TO_IRON_TEMPLATE,"Tempered Steel");
        translationBuilder.add(ModItems.IRON_TO_GOLD_TEMPLATE,"Golden Law");
        translationBuilder.add(ModItems.GOLD_TO_DIAMOND_TEMPLATE,"Stellar Brilliance");
        translationBuilder.add(ModItems.DIAMOND_TO_NETHERITE_TEMPLATE,"Primal Embers");

        //翻译模版适用于...
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_wood_spear","Applies to Wood Spear");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_stone_spear","Applies to Stone Spear");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_copper_spear","Applies to Copper Spear");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_iron_spear","Applies to Iron Spear");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_gold_spear","Applies to Gold Spear");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_diamond_spear","Applies to Diamond Spear");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_netherite_spear","Applies to Netherite Spear");

        //材料...
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_stone","Ingredients: Stone");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_copper","Ingredients: Copper");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_iron","Ingredients: Iron");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_gold","Ingredients: Gold");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_diamond","Ingredients: Diamond");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_netherite","Ingredients: Netherite");
    }
}
