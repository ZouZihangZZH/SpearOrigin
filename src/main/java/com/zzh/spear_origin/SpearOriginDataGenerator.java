package com.zzh.spear_origin;

import com.zzh.spear_origin.datagen.ModChineseLangProvider;
import com.zzh.spear_origin.datagen.ModEnglishLangProvider;
import com.zzh.spear_origin.datagen.ModModelProvider;
import com.zzh.spear_origin.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SpearOriginDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		//语言文件
		pack.addProvider(ModEnglishLangProvider::new);
		pack.addProvider(ModChineseLangProvider::new);
		//模型JSON
		pack.addProvider(ModModelProvider::new);
		//模版合成配方
		pack.addProvider(ModRecipeProvider::new);
	}
}
