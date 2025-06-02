package net.generic404_.keranite.damagetype;

import net.generic404_.keranite.Keranite;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> DISCHARGED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Keranite.MOD_ID, "discharged"));
    public static final RegistryKey<DamageType> SHOCKWAVE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Keranite.MOD_ID, "shockwave"));

    public static DamageSource of(World world, RegistryKey<DamageType> key){
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
