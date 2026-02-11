package com.zzh.spear_origin.datagen;

import com.zzh.spear_origin.block.ModBlocks;
import com.zzh.spear_origin.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModChineseLangProvider extends FabricLanguageProvider {
    public ModChineseLangProvider(FabricDataOutput dataOutput) {
        // ⚠️ 注意：这里必须是 "zh_cn"，代表简体中文
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        // --- 1. 基础物品与方块 ---
        translationBuilder.add(ModItems.WOOD_SPEAR, "木枪");
        translationBuilder.add(ModItems.STONE_SPEAR, "石枪");
        translationBuilder.add(ModItems.COPPER_SPEAR, "铜枪");
        translationBuilder.add(ModItems.IRON_SPEAR, "铁枪");
        translationBuilder.add(ModItems.GOLD_SPEAR, "金枪");
        translationBuilder.add(ModItems.DIAMOND_SPEAR, "钻石枪");
        translationBuilder.add(ModItems.NETHERITE_SPEAR, "下界合金枪");

        translationBuilder.add(ModBlocks.SPEAR_REFORGING_TABLE, "归元台");
        translationBuilder.add("container.spear_origin.spear_reforging", "归元台"); // GUI 标题
        translationBuilder.add("itemGroup.spear_origin", "枪之归元"); // 创造模式物品栏

        // --- 2. 模版名称 (风格化翻译) ---
        // 对应英文: Rock Solid Reforging
        translationBuilder.add(ModItems.WOOD_TO_STONE_TEMPLATE, "磐石重铸");
        // 对应英文: Copper Smelting
        translationBuilder.add(ModItems.STONE_TO_COPPER_TEMPLATE, "熔铜铸炼");
        // 对应英文: Tempered Steel
        translationBuilder.add(ModItems.COPPER_TO_IRON_TEMPLATE, "百炼成钢");
        // 对应英文: Golden Law
        translationBuilder.add(ModItems.IRON_TO_GOLD_TEMPLATE, "黄金律法");
        // 对应英文: Stellar Brilliance
        translationBuilder.add(ModItems.GOLD_TO_DIAMOND_TEMPLATE, "星辰璀璨");
        // 对应英文: Primal Embers
        translationBuilder.add(ModItems.DIAMOND_TO_NETHERITE_TEMPLATE, "原始余烬");

        // --- 3. 模版详细描述 ---
        // 逻辑必须与 ModItems 里的 upgradeName ("wood_spear", "stone_spear"...) 一致

        translationBuilder.add("item.spear_origin.name_upgrade_text", "长枪进阶");
        translationBuilder.add("item.spear_origin.base_slot_text", "放置长枪");
        translationBuilder.add("item.spear_origin.additions_slot_text", "放置材料");

        // [木 -> 石]
        translationBuilder.add("item.spear_origin.smithing_template.wood_spear.applies_to", "木枪");
        translationBuilder.add("item.spear_origin.smithing_template.wood_spear.ingredients", "平滑石头");
        translationBuilder.add("item.spear_origin.smithing_template.wood_spear.base_slot_description", "放入木枪");
        translationBuilder.add("item.spear_origin.smithing_template.wood_spear.additions_slot_description", "放入平滑石头");
        translationBuilder.add("upgrade.spear_origin.wood_spear", "重铸为石枪"); // GUI 顶部标题

        // [石 -> 铜]
        translationBuilder.add("item.spear_origin.smithing_template.stone_spear.applies_to", "石枪");
        translationBuilder.add("item.spear_origin.smithing_template.stone_spear.ingredients", "铜块");
        translationBuilder.add("item.spear_origin.smithing_template.stone_spear.base_slot_description", "放入石枪");
        translationBuilder.add("item.spear_origin.smithing_template.stone_spear.additions_slot_description", "放入铜块");
        translationBuilder.add("upgrade.spear_origin.stone_spear", "重铸为铜枪");

        // [铜 -> 铁]
        translationBuilder.add("item.spear_origin.smithing_template.copper_spear.applies_to", "铜枪");
        translationBuilder.add("item.spear_origin.smithing_template.copper_spear.ingredients", "铁块");
        translationBuilder.add("item.spear_origin.smithing_template.copper_spear.base_slot_description", "放入铜枪");
        translationBuilder.add("item.spear_origin.smithing_template.copper_spear.additions_slot_description", "放入铁块");
        translationBuilder.add("upgrade.spear_origin.copper_spear", "重铸为铁枪");

        // [铁 -> 金]
        translationBuilder.add("item.spear_origin.smithing_template.iron_spear.applies_to", "铁枪");
        translationBuilder.add("item.spear_origin.smithing_template.iron_spear.ingredients", "金块");
        translationBuilder.add("item.spear_origin.smithing_template.iron_spear.base_slot_description", "放入铁枪");
        translationBuilder.add("item.spear_origin.smithing_template.iron_spear.additions_slot_description", "放入金块");
        translationBuilder.add("upgrade.spear_origin.iron_spear", "重铸为金枪");

        // [金 -> 钻石]
        translationBuilder.add("item.spear_origin.smithing_template.gold_spear.applies_to", "金枪");
        translationBuilder.add("item.spear_origin.smithing_template.gold_spear.ingredients", "钻石块");
        translationBuilder.add("item.spear_origin.smithing_template.gold_spear.base_slot_description", "放入金枪");
        translationBuilder.add("item.spear_origin.smithing_template.gold_spear.additions_slot_description", "放入钻石块");
        translationBuilder.add("upgrade.spear_origin.gold_spear", "重铸为钻石枪");

        // [钻石 -> 下界合金]
        translationBuilder.add("item.spear_origin.smithing_template.diamond_spear.applies_to", "钻石枪");
        translationBuilder.add("item.spear_origin.smithing_template.diamond_spear.ingredients", "下界合金块");
        translationBuilder.add("item.spear_origin.smithing_template.diamond_spear.base_slot_description", "放入钻石枪");
        translationBuilder.add("item.spear_origin.smithing_template.diamond_spear.additions_slot_description", "放入下界合金锭块");
        translationBuilder.add("upgrade.spear_origin.diamond_spear", "重铸为下界合金枪");
    }
}