package net.generic404_.keranite.item;

import net.generic404_.keranite.Keranite;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum KeraniteArmorMaterials implements ArmorMaterial {
    LIGHT("light",24,new int[] {3,8,6,3},22,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,1f,0.1f,()->Ingredient.ofItems(ModItems.KERANITE_INGOT)),
    HEAVY("heavy",27,new int[] {4,9,5,4},22,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,3f,0.1f,()->Ingredient.ofItems(ModItems.KERANITE_INGOT)),

    SOCK("sock",8,new int[] {1,3,2,1},22,
            SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,0f,0f,()->Ingredient.ofItems(Items.STRING));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11,16,15,13};

    KeraniteArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound,
                           float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Keranite.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
