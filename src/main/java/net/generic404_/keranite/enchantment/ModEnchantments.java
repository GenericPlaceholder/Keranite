package net.generic404_.keranite.enchantment;

import net.generic404_.keranite.Keranite;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final Enchantment CONDUCTOR = registerEnchant("conductor", new Conductor());
    public static final Enchantment VANISH = registerEnchant("vanish", new RapierEnchant());
    public static final Enchantment DISCHARGE = registerEnchant("discharge", new Discharge());
    public static final Enchantment SHOCKWAVE = registerEnchant("shockwave", new Shockwave());
    public static final Enchantment GUST = registerEnchant("gust", new Gust());
    public static final Enchantment FORCE = registerEnchant("force", new Force());
    public static final Enchantment MAGNETIZED = registerEnchant("magnetized", new Magnetized());

    private static Enchantment registerEnchant(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(Keranite.MOD_ID, name), enchantment);
    }

    public static void registerModEnchants(){

    }
}
