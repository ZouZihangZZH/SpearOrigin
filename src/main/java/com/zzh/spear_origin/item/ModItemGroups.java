package com.zzh.spear_origin.item;

import com.zzh.spear_origin.SpearOrigin;
import com.zzh.spear_origin.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    //注册物品组
    public static final ItemGroup SPEAR_ORIGIN = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(SpearOrigin.MOD_ID, "spear_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.spear_origin"))
                    .icon(() -> new ItemStack(ModItems.WOOD_SPEAR))
                    .entries((displayContext, entries) -> {
                        //添加武器
                        entries.add(ModItems.WOOD_SPEAR);
                        entries.add(ModItems.STONE_SPEAR);
                        entries.add(ModItems.COPPER_SPEAR);
                        entries.add(ModItems.IRON_SPEAR);
                        entries.add(ModItems.GOLD_SPEAR);
                        entries.add(ModItems.DIAMOND_SPEAR);
                        entries.add(ModItems.NETHERITE_SPEAR);
                        //添加模版
                        entries.add(ModItems.WOOD_TO_STONE_TEMPLATE);
                        entries.add(ModItems.STONE_TO_COPPER_TEMPLATE);
                        entries.add(ModItems.COPPER_TO_IRON_TEMPLATE);
                        entries.add(ModItems.IRON_TO_GOLD_TEMPLATE);
                        entries.add(ModItems.GOLD_TO_DIAMOND_TEMPLATE);
                        entries.add(ModItems.DIAMOND_TO_NETHERITE_TEMPLATE);
                        //添加归元台
                        entries.add(ModBlocks.SPEAR_REFORGING_TABLE);
                    }).build());

    //初始化方法
    public static void registerItemGroups(){

    }
}

