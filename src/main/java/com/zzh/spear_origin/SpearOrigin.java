package com.zzh.spear_origin;

import com.zzh.spear_origin.block.ModBlocks;
import com.zzh.spear_origin.block.entity.ModBlockEntities;
import com.zzh.spear_origin.item.ModItemGroups;
import com.zzh.spear_origin.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpearOrigin implements ModInitializer {
	public static final String MOD_ID = "spear_origin";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModBlocks.registerModBlocks();
	}
}