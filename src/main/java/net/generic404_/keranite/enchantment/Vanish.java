package net.generic404_.keranite.enchantment;

import net.generic404_.keranite.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Vanish extends Enchantment {
    public Vanish() {
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
    @Override
    public boolean isAcceptableItem(@NotNull ItemStack stack) {
        return stack.isOf(ModItems.KERANITE_RAPIER);
    }

//    public void onTargetDamaged(@NotNull LivingEntity user, Entity target, int level) {
//        if(user.getMainHandStack().getItem()==ModItems.KERANITE_RAPIER) {
//            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY,10*level,0,false,true,true));
//            if(target.isAlive()){
//                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,10*level,0,false,true,true));
//            }
//        }
//        super.onTargetDamaged(user, target, level);
//    }
}
