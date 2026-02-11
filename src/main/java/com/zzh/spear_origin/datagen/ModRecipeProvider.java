package com.zzh.spear_origin.datagen;

import com.zzh.spear_origin.block.ModBlocks;
import com.zzh.spear_origin.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        //初级模版
        offerTemplateCreationRecipe(exporter,
                ModItems.WOOD_TO_STONE_TEMPLATE,
                Items.OAK_LOG,
                Items.STONE,
                Items.SMOOTH_STONE
        );
        offerTemplateCreationRecipe(exporter,
                ModItems.STONE_TO_COPPER_TEMPLATE,
                Items.STONE,
                Items.COPPER_BLOCK,
                Items.COPPER_INGOT
        );
        offerTemplateCreationRecipe(exporter,
                ModItems.COPPER_TO_IRON_TEMPLATE,
                Items.COPPER_BLOCK,
                Items.IRON_INGOT,
                Items.IRON_NUGGET
        );
        offerTemplateCreationRecipe(exporter,
                ModItems.IRON_TO_GOLD_TEMPLATE,
                Items.IRON_BLOCK,
                Items.GOLD_NUGGET,
                Items.GOLD_INGOT
        );

        //高级模版
        offerSeniorTemplateCreationRecipe(
                exporter,
                ModItems.GOLD_TO_DIAMOND_TEMPLATE,
                Items.DIAMOND_HELMET,
                Items.DIAMOND_CHESTPLATE,
                Items.DIAMOND_LEGGINGS,
                Items.DIAMOND_BOOTS,
                Items.DIAMOND_BLOCK,
                Items.DIAMOND
        );
        offerSeniorTemplateCreationRecipe(
                exporter,
                ModItems.DIAMOND_TO_NETHERITE_TEMPLATE,
                Items.NETHERITE_HELMET,
                Items.NETHERITE_CHESTPLATE,
                Items.NETHERITE_LEGGINGS,
                Items.NETHERITE_BOOTS,
                Items.NETHERITE_BLOCK,
                Items.NETHERITE_INGOT
        );

        //武器升级
        offerSpearUpgradeRecipe(exporter,
                ModItems.WOOD_TO_STONE_TEMPLATE,
                ModItems.WOOD_SPEAR,
                Items.COBBLESTONE,
                ModItems.STONE_SPEAR
        );
        offerSpearUpgradeRecipe(exporter,
                ModItems.STONE_TO_COPPER_TEMPLATE,
                ModItems.STONE_SPEAR,
                Items.IRON_BLOCK,
                ModItems.COPPER_SPEAR
        );
        offerSpearUpgradeRecipe(exporter,
                ModItems.COPPER_TO_IRON_TEMPLATE,
                ModItems.COPPER_SPEAR,
                Items.IRON_BLOCK,
                ModItems.IRON_SPEAR
        );
        offerSpearUpgradeRecipe(exporter,
                ModItems.IRON_TO_GOLD_TEMPLATE,
                ModItems.IRON_SPEAR,
                Items.GOLD_BLOCK,
                ModItems.GOLD_SPEAR
        );
        offerSpearUpgradeRecipe(exporter,
                ModItems.GOLD_TO_DIAMOND_TEMPLATE,
                ModItems.GOLD_SPEAR,
                Items.DIAMOND_BLOCK,
                ModItems.DIAMOND_SPEAR
        );
        offerSpearUpgradeRecipe(exporter,
                ModItems.DIAMOND_TO_NETHERITE_TEMPLATE,
                ModItems.DIAMOND_SPEAR,
                Items.NETHERITE_BLOCK,
                ModItems.NETHERITE_SPEAR
        );

        // 归元台合成
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.SPEAR_REFORGING_TABLE)
                .pattern("TTT")
                .pattern(" M ")
                .pattern("B B")

                // 定义材料
                .input('T', Items.COPPER_INGOT)
                .input('M', Items.STONE)
                .input('B', Items.SMOOTH_STONE)

                // 解锁条件：获得铜锭就解锁
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))

                // 生成文件名
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SPEAR_REFORGING_TABLE)));
    }

    /**
     * 辅助生成模版方法
     * @param exporter     配方导出器
     * @param output       做出来的模版
     * @param centerItem   中间那个核心物品 (X)
     * @param cornersItem  四周的物品      (#)
     * @param crossItem    十字的物品      (@)
     */
    private static void offerTemplateCreationRecipe(Consumer<RecipeJsonProvider> exporter,
                                                    ItemConvertible output,
                                                    ItemConvertible centerItem,
                                                    ItemConvertible cornersItem,
                                                    ItemConvertible crossItem) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output, 1) // 产出 1 个
                // 定义 3x3 的形状
                .pattern("#@#")
                .pattern("@X@")
                .pattern("#@#")

                // 定义符号代表什么
                .input('#', cornersItem)
                .input('@', crossItem)
                .input('X', centerItem)

                // 解锁条件：当玩家获得“核心材料”时解锁配方
                .criterion(hasItem(centerItem), conditionsFromItem(centerItem))

                // 文件名：模版名_crafting (例如 wood_to_stone_template_crafting.json)
                .offerTo(exporter, new Identifier(getRecipeName(output)+ "_crafting"));
    }

    /**
     * 辅助生成模版方法
     * @param exporter     配方导出器
     * @param output       做出来的模版
     * @param TLItem       左上
     * @param TRItem       右上
     * @param BLItem       左下
     * @param BRItem       右下
     * @param centerItem   中间那个核心物品 (X)
     * @param crossItem    十字的物品      (@)
     */
    private static void offerSeniorTemplateCreationRecipe(Consumer<RecipeJsonProvider> exporter,
                                                    ItemConvertible output,
                                                    ItemConvertible TLItem,
                                                    ItemConvertible TRItem,
                                                    ItemConvertible BLItem,
                                                    ItemConvertible BRItem,
                                                    ItemConvertible centerItem,
                                                    ItemConvertible crossItem) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output, 1) // 产出 1 个
                // 定义 3x3 的形状
                .pattern("!*@")
                .pattern("*X*")
                .pattern("#*$")

                // 定义符号代表什么
                .input('!', TLItem)
                .input('@', TRItem)
                .input('#', BLItem)
                .input('$', BRItem)
                .input('@', crossItem)
                .input('*', centerItem)

                // 解锁条件：当玩家获得“核心材料”时解锁配方
                .criterion(hasItem(centerItem), conditionsFromItem(centerItem))

                // 文件名：模版名_crafting (例如 wood_to_stone_template_crafting.json)
                .offerTo(exporter, new Identifier(getRecipeName(output)+ "_crafting"));
    }

    /**
     * 辅助生成武器
     * @param exporter       配方导出器
     * @param template       模版
     * @param base           原武器
     * @param addition       升级材料
     * @param result         新武器
     */
    private static void offerSpearUpgradeRecipe(Consumer<RecipeJsonProvider> exporter,
                                                ItemConvertible template,
                                                ItemConvertible base,
                                                ItemConvertible addition,
                                                ItemConvertible result) {

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template),
                        Ingredient.ofItems(base),
                        Ingredient.ofItems(addition),
                        RecipeCategory.COMBAT,
                        result.asItem()
                )
                .criterion(hasItem(template), conditionsFromItem(template))
                .offerTo(exporter, new Identifier(getRecipeName(result) + "_smithing"));
    }
}