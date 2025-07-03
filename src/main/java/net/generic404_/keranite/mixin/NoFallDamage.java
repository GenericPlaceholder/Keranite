package net.generic404_.keranite.mixin;

import net.generic404_.keranite.datagen.ModTagCreator;
import net.generic404_.keranite.util.MiscUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class NoFallDamage {
    @Unique
    protected final LivingEntity self = (LivingEntity) (Object) this;

    @Inject(method="handleFallDamage",at=@At("HEAD"), cancellable = true)
    public void handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if(MiscUtil.isWearingArmorTag(self, ModTagCreator.Items.PREVENTS_FALLDAMAGE)){
            cir.setReturnValue(false);
        }
    }
}
