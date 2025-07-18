package net.generic404_.keranite.effect;

import net.generic404_.keranite.util.RandomUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

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
            int max = 3*amplifier+3;
            int min = max*-1;

            entity.setYaw(entity.getYaw()+ RandomUtil.getRandomInt(min,max));
            entity.setPitch(entity.getPitch()+ RandomUtil.getRandomInt(min,max));
            entity.addVelocity(
                    RandomUtil.getRandomFloat(min,max)/75,
                    entity.isOnGround() ? 0 : RandomUtil.getRandomFloat(min,max)/85,
                    RandomUtil.getRandomFloat(min,max)/75
            );
        }
    }
}