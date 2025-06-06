package net.generic404_.keranite.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Random;

public class Shocked extends StatusEffect {
    protected Shocked(StatusEffectCategory category, int color) {
        super(category, color);
    }

    // This method is called every tick to check whether it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        assert entity.isAlive();
        if(entity.isPlayer()){
            Random rand = new Random();
            int max = 3*amplifier+3;
            int min = max*-1;
            entity.setYaw(entity.getYaw()+rand.nextInt((max-min)+1)+min);
            entity.setPitch(entity.getPitch()+rand.nextInt((max-min)+1)+min);
            entity.addVelocity(
                    (double) (rand.nextInt((max - min) + 1) + min) /85,
                    (double) (rand.nextInt((-min) + 1) + min) /70,
                    (double) (rand.nextInt((max - min) + 1) + min) /85
            );
        }else {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS));
        }
    }
}