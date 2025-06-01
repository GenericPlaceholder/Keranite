package net.generic404_.keranite.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
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
		MinecraftClient client = MinecraftClient.getInstance();
		KeyBinding sprint = client.options.sprintKey;
		/*if(sprint.isPressed()){
			sprint.setPressed(false);
			KeyBinding.unpressAll();
			callbackInfo.cancel();
        }*/
	}
}