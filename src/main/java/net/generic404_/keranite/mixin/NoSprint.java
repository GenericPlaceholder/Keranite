package net.generic404_.keranite.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.generic404_.keranite.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

// thank you so much contributors of Supplementaries, i couldnt figure this out if i tried.

@Mixin(ClientPlayerEntity.class)
public abstract class NoSprint {
	@Shadow @Final protected MinecraftClient client;

	@ModifyReturnValue(method = "canSprint", at=@At("RETURN"))
	private boolean canSprint(boolean original) {
        assert this.client.player != null;
        StatusEffectInstance charged = this.client.player.getStatusEffect(ModEffects.CHARGED);
		StatusEffectInstance shocked = this.client.player.getStatusEffect(ModEffects.SHOCKED);
		return charged == null && shocked == null && original;
    }
}