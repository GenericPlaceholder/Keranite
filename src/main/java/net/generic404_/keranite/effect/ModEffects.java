package net.generic404_.keranite.effect;

import net.generic404_.keranite.Keranite;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect CHARGED = registerEffect("charged", new Charged(StatusEffectCategory.NEUTRAL, 0x4C8FA7).addAttributeModifier(
            EntityAttributes.GENERIC_MOVEMENT_SPEED, "98b7f155-c5b5-4812-825f-2a46a1d3fdc5", -0.10F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL
    ));
    public static final StatusEffect SHOCKED = registerEffect("shocked", new Shocked(StatusEffectCategory.HARMFUL, 0xDBCA73).addAttributeModifier(
            EntityAttributes.GENERIC_MOVEMENT_SPEED, "98b7f155-c5b5-4812-825f-2a46a1d3fdc5", -0.30F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL
    ).addAttributeModifier(
                    EntityAttributes.GENERIC_ATTACK_DAMAGE, "239150a6-a0b6-40dc-922a-d69310be6550", -0.20F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL
            ).addAttributeModifier(
                    EntityAttributes.GENERIC_ATTACK_SPEED, "6f5a4d13-47bd-400c-a0ab-a9423e241d66", -0.20F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL
            )
    );
    public static final StatusEffect VANISHING = registerEffect("vanishing", new Empty(StatusEffectCategory.BENEFICIAL, 0xDBCA73));
    public static final StatusEffect OBFUSCATED = registerEffect("obfuscated", new Empty(StatusEffectCategory.NEUTRAL, 0x000000));
    public static final StatusEffect WARPING  = registerEffect("warping", new Warping(StatusEffectCategory.NEUTRAL, 0xC82FD7));

    public static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Keranite.MOD_ID, name), effect);
    }

    public static void registerModEffects() {}
}
