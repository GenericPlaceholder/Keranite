package net.generic404_.keranite.mixin;

import net.generic404_.keranite.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class ItemRenderingConditional {
    @Inject(method= "renderItem",at=@At("HEAD"),cancellable = true)
    private void onRenderItem(LivingEntity entity, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci){
        if(stack.getItem() == ModItems.KERANITE_BROADSWORD) {
            MinecraftClient client = MinecraftClient.getInstance();

//            ItemRenderer ir = client.getItemRenderer();
            BakedModel model = client.getBakedModelManager().getModel(new Identifier("keranite:item/keranite_broadsword_handheld"));
//
//            matrices.push();
//            ir.renderItem(stack, ModelTransformationMode.FIRST_PERSON_RIGHT_HAND, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, model);
//            matrices.pop();

            ci.cancel();
        }
    }
}
