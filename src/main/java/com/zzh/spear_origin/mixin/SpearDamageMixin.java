package com.zzh.spear_origin.mixin;

import com.zzh.spear_origin.item.custom.SpearItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class SpearDamageMixin {

    // 定义每转增加的攻击力常量
    private static final float DAMAGE_PER_TURN = 0.5f;

    @ModifyVariable(method = "attack", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private float modifyAttackDamage(float originalDamage) {
        // 1. 获取玩家和物品
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack stack = player.getMainHandStack();

        // 2. 确保是我们的长枪
        if (stack.getItem() instanceof SpearItem) {

            // A. 如果断裂：伤害锁定为 1.0 (拳头伤害)
            if (SpearItem.isBroken(stack)) {
                return 1.0f;
            }

            // B. 如果没断：计算加成
            // 原始伤害 (originalDamage) 已经包含了材质基础伤害 (比如木枪是4)

            float bonusDamage = 0.0f;
            if (stack.hasNbt()) {
                NbtCompound nbt = stack.getNbt();

                // 获取转数 (注意 Key 要和你存 NBT 时一致，这里假设是 "Turn")
                int turns = nbt.getInt("Turn");

                // 获取固定伤害加成 (如果你有这个设定)
                float fixedDamage = nbt.getFloat("fixed_damage");

                // 计算总加成
                bonusDamage = (turns * DAMAGE_PER_TURN) + fixedDamage;
            }

            // 返回：原始基础伤害 + 我们的动态加成
            return originalDamage + bonusDamage;
        }

        return originalDamage;
    }
}