package com.zzh.spear_origin.datagen;

import com.zzh.spear_origin.block.ModBlocks;
import com.zzh.spear_origin.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModChineseLangProvider extends FabricLanguageProvider {
    public ModChineseLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        // 物品翻译
        translationBuilder.add(ModItems.WOOD_SPEAR, "木枪");
        translationBuilder.add(ModItems.STONE_SPEAR, "石枪");
        translationBuilder.add(ModItems.COPPER_SPEAR, "铜枪");
        translationBuilder.add(ModItems.IRON_SPEAR, "铁枪");
        translationBuilder.add(ModItems.DIAMOND_SPEAR, "钻石枪");
        translationBuilder.add(ModItems.NETHERITE_SPEAR, "下界合金枪");

        //翻译归元台
        translationBuilder.add(ModBlocks.SPEAR_REFORGING_TABLE,"归元台");

        // 物品组
        translationBuilder.add("itemGroup.spear_origin", "枪道：归元");

        //翻译模版
        translationBuilder.add(ModItems.WOOD_TO_STONE_TEMPLATE, "磐石重铸");
        translationBuilder.add(ModItems.STONE_TO_COPPER_TEMPLATE, "赤铜冶炼");
        translationBuilder.add(ModItems.COPPER_TO_IRON_TEMPLATE, "百炼成钢");
        translationBuilder.add(ModItems.IRON_TO_GOLD_TEMPLATE, "黄金律法");
        translationBuilder.add(ModItems.GOLD_TO_DIAMOND_TEMPLATE, "璀璨星陨");
        translationBuilder.add(ModItems.DIAMOND_TO_NETHERITE_TEMPLATE, "极境余烬");

        //翻译适用于...
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_wood_spear","适用木枪");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_stone_spear","适用石枪");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_copper_spear","适用铜枪");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_iron_spear","适用铁枪");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_gold_spear","适用金枪");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.applies_to_diamond_spear","适用钻石枪");

        //翻译材料...
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_stone","所需材料：石");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_copper","所需材料：铜");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_iron","所需材料：铁");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_gold","所需材料：金");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_diamond","所需材料：钻石");
        translationBuilder.add("item.spear_origin.smithing_template.wood_upgrade.ingredients_netherite","所需材料：下界合金");
    }
}
