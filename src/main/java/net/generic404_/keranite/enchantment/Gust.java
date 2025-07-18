package net.generic404_.keranite.enchantment;

import net.generic404_.keranite.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Gust extends Enchantment {
    public Gust() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }
    @Override
    public boolean isAcceptableItem(@NotNull ItemStack stack) {
        return stack.isOf(ModItems.RAPIER)||stack.isOf(ModItems.JAVELIN);
    }
}
