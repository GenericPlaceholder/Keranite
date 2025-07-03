package net.generic404_.keranite.enchantment;

import net.generic404_.keranite.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Magnetized extends Enchantment {
    public Magnetized() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }
    @Override
    public boolean isAcceptableItem(@NotNull ItemStack stack) {
        return stack.isOf(ModItems.BROADSWORD)||stack.isOf(ModItems.RAPIER)||stack.isOf(ModItems.JAVELIN);
    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        target.addVelocity(user.getPos().subtract(target.getPos()).multiply(0.2));
    }
}
