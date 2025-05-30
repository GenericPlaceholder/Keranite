package net.generic404_.keranite.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

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

    // lowkey? doesnt seem fun to fight.
//    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
//        if(target instanceof LivingEntity) {
//            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.CHARGED, 60, 1));
//        }
//
//        super.onTargetDamaged(user, target, level);
//    }
}
