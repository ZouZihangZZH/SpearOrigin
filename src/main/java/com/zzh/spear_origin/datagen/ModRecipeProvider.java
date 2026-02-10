package com.zzh.spear_origin.datagen;

import com.zzh.spear_origin.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder; // 关键类：有序合成
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        //
        offerTemplateCreationRecipe(exporter,
                ModItems.WOOD_TO_STONE_TEMPLATE,
                Items.OAK_LOG,
                Items.STONE,
                Items.SMOOTH_STONE
        );

        // 🌟 2. 制造“石转铜”模版 (假设)
        // 逻辑：中间放 石头，周围围一圈 铜锭
        /*
        offerTemplateCreationRecipe(exporter,
            ModItems.STONE_TO_COPPER_TEMPLATE,
            Items.STONE,
            Items.COPPER_INGOT
        );
        */

        // ...以此类推
    }

    /**
     * 辅助方法
     * @param exporter   配方导出器
     * @param output     做出来的模版
     * @param centerItem 中间那个核心物品 (X)
     * @param cornersItem  四周的物品 (#)
     * @param crossItem  十字的物品 (@)
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
                .offerTo(exporter, new Identifier(getRecipeName(output)));
    }
}