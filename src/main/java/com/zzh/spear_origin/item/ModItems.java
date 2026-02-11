package com.zzh.spear_origin.item;

import com.zzh.spear_origin.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    //辅助武器组册方法
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("spear_origin", name), item);
    }

    //辅助模版组册方法
    private static Item registerSmithingTemplate(String name, String appliesToKey, String ingredientsKey) {
        //定义模版在“基础物品槽（左）”显示的空图标
        List<Identifier> emptyBaseSlotTextures = Util.make(new ArrayList<>(), list -> {
            list.add(new Identifier("item/empty_slot_sword"));
            list.add(new Identifier("item/empty_slot_pickaxe"));
        });

        //定义模版在“材料槽（右）”显示的空图标
        List<Identifier> emptyAdditionsSlotTextures = Util.make(new ArrayList<>(), list -> {
            list.add(new Identifier("item/empty_slot_ingot"));
        });

        //创建物品
        return registerItem(name, new SmithingTemplateItem(
                Text.translatable(appliesToKey).formatted(Formatting.BLUE), // "适用与..." 的文字
                Text.translatable(ingredientsKey).formatted(Formatting.BLUE), // "材料..." 的文字
                Text.translatable("item.spear_origin.name_upgrade_text"), // 升级界面的标题
                Text.translatable("item.spear_origin.base_slot_text"),    // 左边格子的提示
                Text.translatable("item.spear_origin.additions_slot_text"), // 右边格子的提示
                emptyBaseSlotTextures,
                emptyAdditionsSlotTextures
        ));
    }

    //初始化方法
    public static void registerModItems() {

    }

    /**
     * 注册武器
     */
    public static final Item WOOD_SPEAR = registerItem(
            "wood_spear",
            new SwordItem(ToolMaterials.WOOD, 3, -2.4f, new FabricItemSettings())
    );
    public static final Item STONE_SPEAR = registerItem(
            "stone_spear",
            new SwordItem(ToolMaterials.STONE, 3, -2.4f, new FabricItemSettings())
    );
    public static final Item COPPER_SPEAR = registerItem(
            "copper_spear",
            new SwordItem(ModToolMaterials.COPPER, 3, -2.4f, new FabricItemSettings())
    );
    public static final Item IRON_SPEAR = registerItem(
            "iron_spear",
            new SwordItem(ToolMaterials.IRON, 3, -2.4f, new FabricItemSettings())
    );
    public static final Item GOLD_SPEAR = registerItem(
            "gold_spear",
            new SwordItem(ToolMaterials.GOLD, 3, -2.4f, new FabricItemSettings())
    );
    public static final Item DIAMOND_SPEAR = registerItem(
            "diamond_spear",
            new SwordItem(ToolMaterials.DIAMOND, 3, -2.4f, new FabricItemSettings())
    );
    public static final Item NETHERITE_SPEAR = registerItem(
            "netherite_spear",
            new SwordItem(ToolMaterials.NETHERITE, 3, -2.4f, new FabricItemSettings())
    );

    /**
      注册模版
     */
    public static final Item WOOD_TO_STONE_TEMPLATE = registerSmithingTemplate(
            "wood_to_stone_template",
            "item.spear_origin.smithing_template.wood_upgrade.applies_to_wood_spear",
            "item.spear_origin.smithing_template.wood_upgrade.ingredients_stone"
    );
    public static final Item STONE_TO_COPPER_TEMPLATE = registerSmithingTemplate(
            "stone_to_copper_template",
            "item.spear_origin.smithing_template.wood_upgrade.applies_to_stone_spear",
            "item.spear_origin.smithing_template.wood_upgrade.ingredients_copper"
    );
    public static final Item COPPER_TO_IRON_TEMPLATE = registerSmithingTemplate(
            "copper_to_iron_template",
            "item.spear_origin.smithing_template.wood_upgrade.applies_to_copper_spear",
            "item.spear_origin.smithing_template.wood_upgrade.ingredients_iron"
    );
    public static final Item IRON_TO_GOLD_TEMPLATE = registerSmithingTemplate(
            "iron_to_gold_template",
            "item.spear_origin.smithing_template.wood_upgrade.applies_to_iron_spear",
            "item.spear_origin.smithing_template.wood_upgrade.ingredients_gold"
    );
    public static final Item GOLD_TO_DIAMOND_TEMPLATE = registerSmithingTemplate(
            "gold_to_diamond_template",
            "item.spear_origin.smithing_template.wood_upgrade.applies_to_gold_spear",
            "item.spear_origin.smithing_template.wood_upgrade.ingredients_diamond"
    );
    public static final Item DIAMOND_TO_NETHERITE_TEMPLATE = registerSmithingTemplate(
            "diamond_to_netherite_template",
            "item.spear_origin.smithing_template.wood_upgrade.applies_to_diamond_spear",
            "item.spear_origin.smithing_template.wood_upgrade.ingredients_netherite"
    );

    /**
     * 注册归元台
     */
    public static final Item SPEAR_REFORGING_TABLE = registerItem(
            "spear_reforging_table",
            new BlockItem(ModBlocks.SPEAR_REFORGING_TABLE, new FabricItemSettings())
    );
}
