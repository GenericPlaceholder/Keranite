package net.generic404_.keranite.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.entity.model.JavelinEntityModel;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class JavelinRenderer extends EntityRenderer<TridentEntity> {
    public static final Identifier TEXTURE = new Identifier(Keranite.MOD_ID, "textures/item/javelin");
    private final JavelinEntityModel model;

    public JavelinRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new JavelinEntityModel(context.getPart(EntityModelLayers.TRIDENT));
    }

    @Override
    public Identifier getTexture(TridentEntity entity) {
        return TEXTURE;
    }

//    public void render(JavelinEntity javelinEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
//        matrixStack.push();
//        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, javelinEntity.prevYaw, javelinEntity.getYaw()) - 90.0F));
//        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, javelinEntity.prevPitch, javelinEntity.getPitch()) + 90.0F));
//        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
//                vertexConsumerProvider, this.model.getLayer(this.getTexture(javelinEntity)), false, javelinEntity.isEnchanted()
//        );
//        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
//        matrixStack.pop();
//        super.render(javelinEntity, f, g, matrixStack, vertexConsumerProvider, i);
//    }
}
