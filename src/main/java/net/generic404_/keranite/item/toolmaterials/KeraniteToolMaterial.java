package net.generic404_.keranite.item.toolmaterials;

import net.generic404_.keranite.item.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class KeraniteToolMaterial implements ToolMaterial {
    public static final KeraniteToolMaterial INSTANCE = new KeraniteToolMaterial();

    @Override
    public int getDurability() {
        return 1561;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 9.0F;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 5;
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.KERANITE_INGOT);
    }

    public static void initialize() {}
}
