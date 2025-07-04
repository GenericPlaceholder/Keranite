package net.generic404_.keranite.mixin.itemRendering.bigToSmall;

import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.item.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow public abstract void renderItem(ItemStack stack, ModelTransformationMode transformationType, int light, int overlay, MatrixStack matrices, VertexConsumerProvider vertexConsumers, @Nullable World world, int seed);

    @ModifyVariable(method= "renderItem",at=@At(value="HEAD"),argsOnly=true)
    public BakedModel useBigModel(BakedModel value,
                                  ItemStack stack,
                                  ModelTransformationMode transformationMode,
                                  boolean leftHanded,
                                  MatrixStack matrices,
                                  VertexConsumerProvider consumerProvider,
                                  int light,
                                  int overlay){
        if(stack.isOf(ModItems.BROADSWORD)&&transformationMode!=ModelTransformationMode.GUI){
            if(stack.getOrCreateNbt().getInt("CustomModelData")==1){
                return ((ItemRendererAccessor) this).keranite$getModels().getModelManager().getModel(new ModelIdentifier(Keranite.MOD_ID, "broadsword_handheld_charged", "inventory"));
            } else {
                return ((ItemRendererAccessor) this).keranite$getModels().getModelManager().getModel(new ModelIdentifier(Keranite.MOD_ID, "broadsword_handheld", "inventory"));
            }
        }else if(stack.isOf(ModItems.RAPIER)&&transformationMode!=ModelTransformationMode.GUI){
            return ((ItemRendererAccessor) this).keranite$getModels().getModelManager().getModel(new ModelIdentifier(Keranite.MOD_ID, "rapier_handheld", "inventory"));
        }else if(stack.isOf(ModItems.JAVELIN)&&transformationMode!=ModelTransformationMode.GUI){
            return ((ItemRendererAccessor) this).keranite$getModels().getModelManager().getModel(new ModelIdentifier(Keranite.MOD_ID, "javelin_handheld", "inventory"));
        }
        return value;
    }
}
