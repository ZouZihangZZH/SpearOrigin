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
                        entries.add(ModItems.WOOD_SPEAR);
                        entries.add(ModBlocks.SPEAR_REFORGING_TABLE);
                    }).build());

    //初始化方法
    public static void registerItemGroups(){

    }
}

