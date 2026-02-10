package com.zzh.spear_origin;

import com.zzh.spear_origin.datagen.ModChineseLangProvider;
import com.zzh.spear_origin.datagen.ModEnglishLangProvider;
import com.zzh.spear_origin.datagen.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SpearOriginDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModEnglishLangProvider::new);
		pack.addProvider(ModChineseLangProvider::new);

		pack.addProvider(ModModelProvider::new);
	}
}
