package net.generic404_.keranite.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.generic404_.keranite.effect.ModEffects;
import net.generic404_.keranite.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class ShorterFollowRange {
    @Shadow public abstract Iterable<ItemStack> getArmorItems();

    @ModifyReturnValue(method = "getAttackDistanceScalingFactor", at = @At("RETURN"))
    private double thighhighs(double original, Entity entity) {
        if (entity != null) {
            for ( ItemStack armit : this.getArmorItems() ) {
                if(armit.isOf(ModItems.THIGHHIGHS_PINK)||armit.isOf(ModItems.THIGHHIGHS_BLACK)||armit.isOf(ModItems.THIGHHIGHS_BLUE)){return original / 2;}
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "getAttackDistanceScalingFactor", at = @At("RETURN"))
    private double vanish(double original, Entity entity) {
        if (entity != null) {
            if(entity instanceof LivingEntity livent&&livent.hasStatusEffect(ModEffects.VANISHING)){
                return 0;
            }
        }
        return original;
    }
}