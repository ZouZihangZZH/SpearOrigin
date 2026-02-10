package com.zzh.spear_origin.datagen;

import com.zzh.spear_origin.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModEnglishLangProvider extends FabricLanguageProvider {
    public ModEnglishLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        // 1. 翻译物品
        translationBuilder.add(ModItems.WOOD_SPEAR, "Wood Spear");
        translationBuilder.add(ModItems.STONE_SPEAR, "Stone Spear");
        translationBuilder.add(ModItems.COPPER_SPEAR, "Copper Spear");
        translationBuilder.add(ModItems.IRON_SPEAR, "Iron Spear");
        translationBuilder.add(ModItems.GOLD_SPEAR, "Gold Spear");
        translationBuilder.add(ModItems.DIAMOND_SPEAR, "Diamond Spear");
        translationBuilder.add(ModItems.NETHERITE_SPEAR, "Netherite Spear");

        // 2. 翻译物品组 (需要在 ModItemGroups 里把 key 暴露出来，或者直接用字符串 key)
        translationBuilder.add("itemGroup.spear_origin", "Spear: Origin");

    }
}
