package net.generic404_.keranite.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.generic404_.keranite.damagetype.ModDamageTypes;
import net.generic404_.keranite.item.toolmaterials.KeraniteToolMaterial;
import net.generic404_.keranite.util.NearbyEntitiesUtil;
import net.generic404_.keranite.util.RandomUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.UUID;

public class Verybroadsword extends SwordItem {
    protected static final UUID ATTACK_REACH_MODIFIER_UUID = UUID.fromString("e46b8b05-b0dc-4e15-9f23-faa95529c2ee");
    protected static final UUID REACH_MODIFIER_UUID = UUID.fromString("0daeaf59-fb88-417c-9e59-926926a51be8");

    public Verybroadsword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(KeraniteToolMaterial.KERANITE, 15, -3.3f, settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot){
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_UUID, "the modifier of weapons",
                2d, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_UUID, "the modifier of weapons",
                3d, EntityAttributeModifier.Operation.ADDITION));
        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        String enchantz = user.getStackInHand(hand).getEnchantments().toString();
        String[] enchants = enchantz.split("\"");
        boolean hasConduct = false;
        boolean hasShockwave = false;
        for (String enchant : enchants) {
            if (enchant.equals("keranite:conductor")) {
                hasConduct = true;
            }
            if (enchant.equals("keranite:shockwave")) {
                hasShockwave = true;
            }
        }
        if (hasConduct) {
            NbtCompound tag = user.getStackInHand(hand).getOrCreateNbt();
            int maxmeter = 10;
            if(!tag.getBoolean("charged") &&tag.getInt("meter")>=maxmeter) {
                tag.putBoolean("charged", true);
                return TypedActionResult.consume(user.getStackInHand(hand));
            }else{
                return TypedActionResult.pass(user.getStackInHand(hand));
            }
        } else if(hasShockwave) {
            user.setVelocity(new Vec3d(0,1.5,0));
            ArrayList<Entity> entityList = NearbyEntitiesUtil.getNearbyEntities(user,10,user.getBlockPos());
            for(Entity ent : entityList){
                ent.damage(ModDamageTypes.of(user.getWorld(), ModDamageTypes.SHOCKWAVE), 6);
                ent.addVelocity(new Vec3d(0,1,0));
                ent.addVelocity(((ent.getPos().subtract(user.getPos())).multiply(11-user.distanceTo(ent))).multiply(0.2));
            }
            user.fallDistance = -5;
            world.playSoundFromEntity(null,user, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS,1f,0.8f);
            world.playSoundFromEntity(null,user, SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.PLAYERS,0.8f,1f);
            world.playSoundFromEntity(null,user, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,0.6f,1.4f);
            for(int i=0;i<100;i++) {
                world.addParticle(ParticleTypes.ASH, user.getX()+ RandomUtil.getRandomFloat(-5,5), user.getY()+RandomUtil.getRandomFloat(-3,3), user.getZ()+RandomUtil.getRandomFloat(-5,5),0,0,0);
            }
            world.addParticle(ParticleTypes.EXPLOSION,user.getX(),user.getY(),user.getZ(),0,0,0);
            user.getItemCooldownManager().set(this, 80);
            return TypedActionResult.consume(user.getStackInHand(hand));
        } else {
            user.setVelocity(user.getRotationVector().multiply(new Vec3d(-2, 0, -2)));
            user.addVelocity(0,0.2,0);
            user.fallDistance = -3;
            world.playSoundFromEntity(null,user, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS,0.6f,0.8f);
            for(int i=0;i<5;i++) {
                world.addParticle(ParticleTypes.CLOUD, user.getX(), user.getY(), user.getZ(),(double) RandomUtil.getRandomInt(-1, 1) /100, (double) RandomUtil.getRandomInt(-1, 1) /100,(double) RandomUtil.getRandomInt(-1, 1) /100);
            }
            user.getItemCooldownManager().set(this, 30);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
    }
}
