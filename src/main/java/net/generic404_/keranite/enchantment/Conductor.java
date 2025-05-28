package net.generic404_.keranite.enchantment;

import net.generic404_.keranite.effect.ModEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;

public class Conductor extends Enchantment {
    public Conductor() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }
    @Override
    public int getMaxLevel() {
        return 1;
    }

    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.CHARGED, 60, 1));
            //((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.CHARGED.addAttributeModifier("minecraft:generic.movement_speed", "1fdd322c-9633-4915-af70-9862b54503c2", -0.25d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), 100, 1));
        }

        super.onTargetDamaged(user, target, level);
    }
}
