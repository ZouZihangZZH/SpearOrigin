package com.zzh.spear_origin.item.custom;

import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpearItem extends SwordItem {

    private final int requiredTurn; // 自定义属性：转数

    // 主构造函数
    public SpearItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, int requiredTurn) {
        super(toolMaterial, attackDamage, attackSpeed, settings);//继承父类属性
        this.requiredTurn = requiredTurn;//自定义属性
    }

    public int getRequiredTurn () {
        return this.requiredTurn;
    }

    /**
     * 物品颜色
     */
    private static @NotNull Formatting getFormatting(int turns) {
        Formatting turnColor;
        if (turns >= 1 && turns <= 3) {
            turnColor = Formatting.WHITE;  // 1-3转：白色
        } else if (turns >= 4 && turns <= 6) {
            turnColor = Formatting.BLUE;   // 4-6转：蓝色
        } else if (turns >= 7 && turns <= 8) {
            turnColor = Formatting.LIGHT_PURPLE; // 7-8转：紫色 (或者用 DARK_PURPLE)
        } else if (turns >= 9) {
            turnColor = Formatting.GOLD;   // 9转：金色
        } else {
            turnColor = Formatting.GRAY;   // 兜底颜色
        }
        return turnColor;
    }
    @Override
    public Text getName(ItemStack stack) {
        int turns = stack.getOrCreateNbt().getInt("turns");

        // 如果没有转数，返回原名
        if (turns <= 0) {
            return super.getName(stack);
        }

        // 根据转数确定颜色
        Formatting turnColor = getFormatting(turns);

        // 构建文本：原始翻译键 + [X转]
        return Text.translatable(this.getTranslationKey(stack))
                .append(Text.literal(" [" + turns + "转]"))
                .formatted(turnColor);
    }

    /**
     * 判断是否折断
     * 逻辑：如果当前损伤值 >= 最大耐久度 - 1，就视为折断
     */
    public static boolean isBroken(ItemStack stack) {
        return stack.getDamage() >= stack.getMaxDamage() - 1;
    }

    /**
     * 核心逻辑：攻击实体
     * 包含了：阶梯损耗计算 + 不毁坏逻辑
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 1. 如果是客户端，不处理逻辑 (只处理视觉)
        if (attacker.getWorld().isClient) return true;

        // 2. 如果已经断了，直接返回 (不扣耐久，但保留攻击动作)
        if (isBroken(stack)) {
            return true;
        }

        // --- 3. 阶梯损耗计算开始 ---
        int hitCount = 1; // 默认为 1 (攻击的目标)

        // 定义扫描范围 (模拟横扫攻击的范围)
        Box scanArea = target.getBoundingBox().expand(1.0, 0.25, 1.0);
        List<LivingEntity> nearbyEntities = attacker.getWorld().getEntitiesByClass(
                LivingEntity.class,
                scanArea,
                (entity) -> entity != attacker && entity != target && entity.isAlive()
        );

        // 统计同时也受到了伤害的生物 (判断 hurtTime > 0)
        for (LivingEntity e : nearbyEntities) {
            if (e.hurtTime > 0 || e.deathTime > 0) {
                hitCount++;
            }
        }

        // 阶梯损耗公式：1个怪扣1点，2个怪扣3点，3个怪扣6点...
        int durabilityCost = (hitCount * (hitCount + 1)) / 2;
        // ------------------------

        // 4. 手动应用耐久损耗 (绝不调用 stack.damage)
        int currentDamage = stack.getDamage();
        int maxDamage = stack.getMaxDamage();
        int newDamage = currentDamage + durabilityCost;

        // 5. 临界点检查 (是否会断裂)
        if (newDamage >= maxDamage - 1) {
            // A. 卡在最后 1 点耐久
            stack.setDamage(maxDamage - 1);

            // B. 设置 NBT 标记 (可选，方便其他模组或 Mixin 读取)
            stack.getOrCreateNbt().putBoolean("Broken", true);

            // C. 播放断裂音效 (仅在由好变坏的那一瞬间播放)
            if (currentDamage < maxDamage - 1 && attacker instanceof PlayerEntity player) {
                player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
                // 发送装备损坏状态包 (让角色手里的物品闪一下红)
                player.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
            }
        } else {
            // D. 还没断，正常应用新的损伤值
            stack.setDamage(newDamage);
        }

        return true;
    }

    /**
     * 挖掘逻辑 (也需要防止破坏)
     */
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (isBroken(stack)) return false;

        if (state.getHardness(world, pos) != 0.0F) {
            int newDamage = stack.getDamage() + 2;
            if (newDamage >= stack.getMaxDamage() - 1) {
                stack.setDamage(stack.getMaxDamage() - 1);
                stack.getOrCreateNbt().putBoolean("Broken", true);
                if (miner instanceof PlayerEntity player) {
                    player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
                }
            } else {
                stack.setDamage(newDamage);
            }
        }
        return true;
    }

    // 挖掘速度惩罚
    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (isBroken(stack)) {
            return 0.1f; // 断了之后，挖掘速度极慢
        }
        return super.getMiningSpeedMultiplier(stack, state);
    }

    // 属性修改器 (保持默认，逻辑由 Mixin 接管)
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return super.getAttributeModifiers(slot);
    }

    /**
     * 物品界面显示 (Tooltip)
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (isBroken(stack)) {
            tooltip.add(Text.literal("枪杆已断，威力尽失！").formatted(Formatting.RED));
            return;
        }

        if (stack.hasNbt()) {
            NbtCompound nbt = stack.getNbt();
            int turns = nbt.getInt("Turn");

            if (turns > 0) {
                tooltip.add(Text.literal("§7当前境界: §6" + turns + " 转").formatted(Formatting.GRAY));
                float bonus = turns * 0.5f;
                tooltip.add(Text.literal("§7 ⚔ 境界加成: §9+" + bonus + " 攻击力").formatted(Formatting.BLUE));
            }
        }

        super.appendTooltip(stack, world, tooltip, context);
    }
}