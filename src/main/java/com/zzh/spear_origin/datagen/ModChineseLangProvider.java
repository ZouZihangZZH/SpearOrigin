package com.zzh.spear_origin.datagen;

import com.zzh.spear_origin.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModChineseLangProvider extends FabricLanguageProvider {
    public ModChineseLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        // 在这里写中文翻译
        translationBuilder.add(ModItems.WOOD_SPEAR, "木枪");
        translationBuilder.add(ModItems.STONE_SPEAR, "石枪");
        translationBuilder.add(ModItems.COPPER_SPEAR, "铜枪");
        translationBuilder.add(ModItems.IRON_SPEAR, "铁枪");
        translationBuilder.add(ModItems.DIAMOND_SPEAR, "钻石枪");
        translationBuilder.add(ModItems.NETHERITE_SPEAR, "下界合金枪");

        // 物品组
        translationBuilder.add("itemGroup.spear_origin", "枪道：归元");
    }
}
