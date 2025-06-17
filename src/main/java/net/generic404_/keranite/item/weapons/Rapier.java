package net.generic404_.keranite.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.generic404_.keranite.effect.ModEffects;
import net.generic404_.keranite.item.toolmaterials.KeraniteToolMaterial;
import net.generic404_.keranite.util.NearbyUtil;
import net.generic404_.keranite.util.RandomUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.UUID;

public class Rapier extends SwordItem {
    protected static final UUID ATTACKREACH_MODIFIER_UUID = UUID.fromString("a92256bc-0efa-47ed-aab1-adea724a852c");
    protected static final UUID REACH_MODIFIER_UUID = UUID.fromString("603cbd44-8360-4df7-b10a-2670a4414694");
    public Rapier(Settings settings) {
        super(KeraniteToolMaterial.KERANITE, 9, (1.3f - 4f), settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACKREACH_MODIFIER_UUID, "Weapon Modifier", 0.5d, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_UUID, "Weapon Modifier", 1d, EntityAttributeModifier.Operation.ADDITION));
        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ArrayList<World> NearbyWorlds = NearbyUtil.getWorlds(user, 100, user.getBlockPos());

            NbtList enchants = user.getStackInHand(hand).getEnchantments();
            boolean hasVanish = false;
            boolean hasGust = false;
            for (NbtElement element : enchants) {
                for (String elment : element.toString().split("\"")) {
                    if(elment.equals("keranite:vanish")) {
                        hasVanish = true;
                    }
                    if(elment.equals("keranite:gust")) {
                        hasGust = true;
                    }
                }
            }
            if (hasVanish) {
                user.setVelocity(0, 0.2, 0);
                user.addVelocity(user.getRotationVector().multiply(new Vec3d(1, 0, 1).multiply(-1)));
                user.addStatusEffect(new StatusEffectInstance(ModEffects.VANISHING, 40, 0, false, true, false));
                user.getItemCooldownManager().set(this, 250);
                world.playSoundFromEntity(null,user, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS,0.6f,0.8f);
                world.playSoundFromEntity(null,user, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.PLAYERS,1.2f,0.9f);
                for(int i=0;i<10;i++) {
                    world.addParticle(ParticleTypes.ELECTRIC_SPARK, user.getX(), user.getY(), user.getZ(),RandomUtil.getRandomFloat(-1,1),RandomUtil.getRandomFloat(-1,1),RandomUtil.getRandomFloat(-1,1));
                }
                return TypedActionResult.consume(user.getStackInHand(hand));
            } else if (hasGust) {
                ArrayList<Entity> nearbyEntities = NearbyUtil.getByLivingEntity(user,8,user.getBlockPos());
                Vec3d movementvec = user.getRotationVector().multiply(2);
                for(Entity ent : nearbyEntities){
                    ent.setVelocity(movementvec);
                }
                user.getItemCooldownManager().set(this, 150);
                return TypedActionResult.success(user.getStackInHand(hand),true);
            } else {
                if (!user.isFallFlying()) {
                    user.addVelocity(user.getRotationVector().multiply(new Vec3d(1, 1, 1).multiply(1)));
                    user.fallDistance = -3;
                    user.getItemCooldownManager().set(this, 50);
                } else {
                    user.addVelocity(user.getRotationVector().multiply(new Vec3d(1, 1, 1).multiply(1.5)));
                    user.getItemCooldownManager().set(this, 100);
                }

                for(World world1 : NearbyWorlds){
                    world1.playSoundFromEntity(null,user, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS,0.7f,0.8f);
                    for(int i=0;i<5;i++) {
                        world1.addParticle(ParticleTypes.CLOUD, user.getX(), user.getY(), user.getZ(),(double) RandomUtil.getRandomInt(-1, 1) /100, (double) RandomUtil.getRandomInt(-1, 1) /100,(double) RandomUtil.getRandomInt(-1, 1) /100);
                    }
                }

                return TypedActionResult.consume(user.getStackInHand(hand));
            }
    }
}