package net.generic404_.keranite.enchantment;

import net.generic404_.keranite.effect.ModEffects;
import net.generic404_.keranite.item.ModItems;
import net.generic404_.keranite.util.RandomUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Conductor extends Enchantment {
    public Conductor() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }
    @Override
    public int getMaxLevel() {
        return 2;
    }
    @Override
    public boolean isAcceptableItem(@NotNull ItemStack stack) {
        return stack.isOf(ModItems.BROADSWORD);
    }

    public void onTargetDamaged(@NotNull LivingEntity user, Entity target, int level) {
        World world = user.getWorld();
        if(user.getMainHandStack().getItem()==ModItems.BROADSWORD && Objects.requireNonNull(user.getMainHandStack().getNbt()).getBoolean("charged")){
            if (target instanceof LivingEntity) {
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.CHARGED, 300*level, 0));
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.SHOCKED, 30*level, (-1)+level));

                user.getMainHandStack().getOrCreateNbt().putBoolean("charged",false);
                user.getMainHandStack().getOrCreateNbt().putInt("meter", 0);
                user.getMainHandStack().getOrCreateNbt().putInt("CustomModelData", 0);

                world.playSoundFromEntity(null,target,SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.PLAYERS,1f,0.9f);

                for(int i=0;i<10;i++) {
                    world.addParticle(ParticleTypes.ELECTRIC_SPARK, target.getX()+RandomUtil.getRandomFloat(-0.5f,0.5f), target.getY()+RandomUtil.getRandomFloat(-0.5f,0.5f), target.getZ()+RandomUtil.getRandomFloat(-0.5f,0.5f), 0,0,0);
                }
            }
        }
        int maxmeter = 10;
        int meter = Objects.requireNonNull(user.getMainHandStack().getNbt()).getInt("meter");
        if(Objects.requireNonNull(user.getMainHandStack().getNbt()).getInt("meter")<maxmeter){
            // make it check if player is in creative. to avoid desync
            if (target instanceof PlayerEntity plr) {
                if (!plr.isCreative()) {
                    user.getMainHandStack().getOrCreateNbt().putInt("meter", user.getMainHandStack().getNbt().getInt("meter") + 1);
                }
            }else{
                user.getMainHandStack().getOrCreateNbt().putInt("meter", user.getMainHandStack().getNbt().getInt("meter") + 1);
            }
        }else if(meter>=maxmeter&&meter<(maxmeter+2)){
            if(world.isClient) {
                user.sendMessage(Text.of("[ Charge ready! ]"));
            }
            user.getMainHandStack().getOrCreateNbt().putInt("meter",maxmeter+2);
            user.getMainHandStack().getOrCreateNbt().putInt("CustomModelData", 1);
            world.playSoundFromEntity(null,target,SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS,3f,1.1f);
        }
        super.onTargetDamaged(user, target, level);
    }
}
