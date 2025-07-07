package net.generic404_.keranite;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.generic404_.keranite.entity.CustomEntities;
import net.generic404_.keranite.entity.render.JavelinRenderer;
import net.generic404_.keranite.particle.ParticleInitializer;
import net.minecraft.client.particle.EndRodParticle;

public class KeraniteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ParticleInitializer.CHARGED_TRAIL, EndRodParticle.Factory::new);
        EntityRendererRegistry.register(CustomEntities.JAVELIN_ENTITY_TYPE, JavelinRenderer::new);
    }
}
