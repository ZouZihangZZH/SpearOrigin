package com.zzh.spear_origin.item;

import com.google.common.base.Suppliers;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {

    //定义材质
    // 参数顺序：挖掘等级, 耐久度, 挖掘速度, 攻击力加成, 附魔值, 修复材料
    COPPER(1, 200, 5.0F, 1.5F, 15, () -> Ingredient.ofItems(Items.COPPER_BLOCK));

    // 标准模版
    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() { return this.itemDurability; }
    @Override
    public float getMiningSpeedMultiplier() { return this.miningSpeed; }
    @Override
    public float getAttackDamage() { return this.attackDamage; }
    @Override
    public int getMiningLevel() { return this.miningLevel; }
    @Override
    public int getEnchantability() { return this.enchantability; }
    @Override
    public Ingredient getRepairIngredient() { return this.repairIngredient.get(); }
}