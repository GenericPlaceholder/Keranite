package net.generic404_.keranite;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.generic404_.keranite.entity.CustomEntities;
import net.generic404_.keranite.entity.render.JavelinRenderer;

public class KeraniteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        ParticleFactoryRegistry.getInstance().register(ParticleInitializer.CHARGED_TRAIL, new PortalParticle.Factory());

        EntityRendererRegistry.register(CustomEntities.JAVELIN_ENTITY_TYPE, JavelinRenderer::new);
    }
}
