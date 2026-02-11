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
        // --- 1. 基础翻译 ---
        translationBuilder.add(ModItems.WOOD_SPEAR, "Wood Spear");
        translationBuilder.add(ModItems.STONE_SPEAR, "Stone Spear");
        translationBuilder.add(ModItems.COPPER_SPEAR, "Copper Spear");
        translationBuilder.add(ModItems.IRON_SPEAR, "Iron Spear");
        translationBuilder.add(ModItems.GOLD_SPEAR, "Gold Spear");
        translationBuilder.add(ModItems.DIAMOND_SPEAR, "Diamond Spear");
        translationBuilder.add(ModItems.NETHERITE_SPEAR, "Netherite Spear");

        translationBuilder.add(ModBlocks.SPEAR_REFORGING_TABLE, "Origin Pedestal");
        translationBuilder.add("container.spear_origin.spear_reforging", "Origin Pedestal"); // GUI 标题
        translationBuilder.add("itemGroup.spear_origin", "Spear: Origin");

        // --- 2. 模版名称 (自定义霸气名字！) ---
        //前提：你在 ModItems 里用了 new SpearTemplateItem(...)
        translationBuilder.add(ModItems.WOOD_TO_STONE_TEMPLATE, "Rock Solid Reforging");
        translationBuilder.add(ModItems.STONE_TO_COPPER_TEMPLATE, "Copper Smelting");
        translationBuilder.add(ModItems.COPPER_TO_IRON_TEMPLATE, "Tempered Steel");
        translationBuilder.add(ModItems.IRON_TO_GOLD_TEMPLATE, "Golden Law");
        translationBuilder.add(ModItems.GOLD_TO_DIAMOND_TEMPLATE, "Stellar Brilliance");
        translationBuilder.add(ModItems.DIAMOND_TO_NETHERITE_TEMPLATE, "Primal Embers");

        // --- 3. 模版蓝字描述 (必须匹配 ModItems 里的 upgradeName) ---
        // 你在 ModItems 注册时传入的是 "wood_spear", "stone_spear" 等
        // 所以这里的 Key 必须是 item.spear_origin.smithing_template.wood_spear.applies_to

        // [木 -> 石]
        translationBuilder.add("item.spear_origin.smithing_template.wood_spear.applies_to", "Applies to Wood Spear");
        translationBuilder.add("item.spear_origin.smithing_template.wood_spear.ingredients", "Ingredients: Stone");
        translationBuilder.add("item.spear_origin.smithing_template.wood_spear.base_slot_description", "Add Wood Spear");
        translationBuilder.add("item.spear_origin.smithing_template.wood_spear.additions_slot_description", "Add Stone");
        translationBuilder.add("upgrade.spear_origin.wood_spear", "Reforge to Stone"); // 升级界面标题

        // [石 -> 铜]
        translationBuilder.add("item.spear_origin.smithing_template.stone_spear.applies_to", "Applies to Stone Spear");
        translationBuilder.add("item.spear_origin.smithing_template.stone_spear.ingredients", "Ingredients: Copper Ingot");
        translationBuilder.add("item.spear_origin.smithing_template.stone_spear.base_slot_description", "Add Stone Spear");
        translationBuilder.add("item.spear_origin.smithing_template.stone_spear.additions_slot_description", "Add Copper");
        translationBuilder.add("upgrade.spear_origin.stone_spear", "Reforge to Copper");

        // [铜 -> 铁]
        translationBuilder.add("item.spear_origin.smithing_template.copper_spear.applies_to", "Applies to Copper Spear");
        translationBuilder.add("item.spear_origin.smithing_template.copper_spear.ingredients", "Ingredients: Iron Ingot");
        translationBuilder.add("item.spear_origin.smithing_template.copper_spear.base_slot_description", "Add Copper Spear");
        translationBuilder.add("item.spear_origin.smithing_template.copper_spear.additions_slot_description", "Add Iron");
        translationBuilder.add("upgrade.spear_origin.copper_spear", "Reforge to Iron");

        // [铁 -> 金]
        translationBuilder.add("item.spear_origin.smithing_template.iron_spear.applies_to", "Applies to Iron Spear");
        translationBuilder.add("item.spear_origin.smithing_template.iron_spear.ingredients", "Ingredients: Gold Ingot");
        translationBuilder.add("item.spear_origin.smithing_template.iron_spear.base_slot_description", "Add Iron Spear");
        translationBuilder.add("item.spear_origin.smithing_template.iron_spear.additions_slot_description", "Add Gold");
        translationBuilder.add("upgrade.spear_origin.iron_spear", "Reforge to Gold");

        // [金 -> 钻石]
        translationBuilder.add("item.spear_origin.smithing_template.gold_spear.applies_to", "Applies to Gold Spear");
        translationBuilder.add("item.spear_origin.smithing_template.gold_spear.ingredients", "Ingredients: Diamond");
        translationBuilder.add("item.spear_origin.smithing_template.gold_spear.base_slot_description", "Add Gold Spear");
        translationBuilder.add("item.spear_origin.smithing_template.gold_spear.additions_slot_description", "Add Diamond");
        translationBuilder.add("upgrade.spear_origin.gold_spear", "Reforge to Diamond");

        // [钻石 -> 下界合金]
        translationBuilder.add("item.spear_origin.smithing_template.diamond_spear.applies_to", "Applies to Diamond Spear");
        translationBuilder.add("item.spear_origin.smithing_template.diamond_spear.ingredients", "Ingredients: Netherite Ingot");
        translationBuilder.add("item.spear_origin.smithing_template.diamond_spear.base_slot_description", "Add Diamond Spear");
        translationBuilder.add("item.spear_origin.smithing_template.diamond_spear.additions_slot_description", "Add Netherite");
        translationBuilder.add("upgrade.spear_origin.diamond_spear", "Reforge to Netherite");
    }
}