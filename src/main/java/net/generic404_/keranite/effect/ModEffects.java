package net.generic404_.keranite.effect;

import net.generic404_.keranite.Keranite;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect CHARGED = registerEffect("charged", new Charged(StatusEffectCategory.NEUTRAL, 0x4C8FA7));
    public static final StatusEffect SHOCKED = registerEffect("shocked", new Shocked(StatusEffectCategory.HARMFUL, 0xDBCA73));
    public static final StatusEffect VANISHING = registerEffect("vanishing", new Empty(StatusEffectCategory.BENEFICIAL, 0xDBCA73));
    public static final StatusEffect OBFUSCATED = registerEffect("obfuscated", new Empty(StatusEffectCategory.NEUTRAL, 0x000000));
    public static final StatusEffect WARPING  = registerEffect("warping", new Warping(StatusEffectCategory.NEUTRAL, 0xC82FD7));

    public static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Keranite.MOD_ID, name), effect);
    }

    public static void registerModEffects() {}
}
