package com.zzh.spear_origin.item;

import com.zzh.spear_origin.SpearOrigin;
import com.zzh.spear_origin.block.ModBlocks;
import com.zzh.spear_origin.item.custom.SpearTemplateItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.MutableText;
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

    /**
     * 封装模板注册逻辑
     */
    private static Item registerTemplate(String id, String upgradeName) {
        Text appliesTo = t("smithing_template." + upgradeName + ".applies_to").formatted(Formatting.BLUE);
        Text ingredients = t("smithing_template." + upgradeName + ".ingredients").formatted(Formatting.BLUE);
        Text title = Text.translatable(Util.createTranslationKey("upgrade", new Identifier(SpearOrigin.MOD_ID, upgradeName))).formatted(Formatting.GRAY);
        Text baseDesc = t("smithing_template." + upgradeName + ".base_slot_description");
        Text addDesc = t("smithing_template." + upgradeName + ".additions_slot_description");

        List<Identifier> icons = List.of(new Identifier("minecraft", "item/empty_slot_sword"));
        List<Identifier> materials = List.of(new Identifier("minecraft", "item/empty_slot_ingot"));

        // 使用 7 参数构造器
        return registerItem(id, new SpearTemplateItem(appliesTo, ingredients, title, baseDesc, addDesc, icons, materials));
    }

    /**
     * 辅助：翻译文本包装器
     */
    private static MutableText t(String key) {
        return Text.translatable(Util.createTranslationKey("item", new Identifier(SpearOrigin.MOD_ID, key)));
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
    public static final Item WOOD_TO_STONE_TEMPLATE = registerTemplate("wood_spear_template", "wood_spear");
    public static final Item STONE_TO_COPPER_TEMPLATE = registerTemplate("stone_spear_template", "stone_spear");
    public static final Item COPPER_TO_IRON_TEMPLATE = registerTemplate("copper_spear_template", "copper_spear");
    public static final Item IRON_TO_GOLD_TEMPLATE = registerTemplate("iron_spear_template", "iron_spear");
    public static final Item GOLD_TO_DIAMOND_TEMPLATE = registerTemplate("gold_spear_template", "gold_spear");
    public static final Item DIAMOND_TO_NETHERITE_TEMPLATE = registerTemplate("diamond_spear_template", "diamond_spear");
}
