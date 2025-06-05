package net.generic404_.keranite.mixin;

import net.generic404_.keranite.effect.ModEffects;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class Vanish {
    @Inject(method="shouldRender",at=@At("HEAD"), cancellable = true)
    public void onRenderVisible(Entity entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity livent && entity.isAlive()) {
            if (livent.hasStatusEffect(ModEffects.VANISHING)) {
                cir.setReturnValue(false);
            }
        }
    }
}
