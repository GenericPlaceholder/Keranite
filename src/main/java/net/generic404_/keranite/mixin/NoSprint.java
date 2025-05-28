package net.generic404_.keranite.mixin;

import net.generic404_.keranite.Keranite;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class NoSprint {
	@Shadow @Final private MinecraftClient client;



	@Inject(method="onKey",at = @At("HEAD"), cancellable = true)
	public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo callbackInfo) {
        //Keranite.LOGGER.info(String.valueOf(scancode));
	}
}