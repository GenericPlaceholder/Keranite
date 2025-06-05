package net.generic404_.keranite;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.generic404_.keranite.projectiles.CustomProjectile;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class KeraniteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(CustomProjectile.ConcussionBombEntityType, (context) ->
                new FlyingItemEntityRenderer<>(context));
    }
}
