package net.generic404_.keranite.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.generic404_.keranite.enchantment.ModEnchantments;
import net.generic404_.keranite.item.toolmaterials.KeraniteToolMaterial;
import net.generic404_.keranite.util.SpecialUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class Broadsword extends SwordItem {
    protected static final UUID ATTACK_REACH_MODIFIER_UUID = UUID.fromString("1293b11e-094f-4e9b-8f20-4fcbf0cdae25");
    protected static final UUID REACH_MODIFIER_UUID = UUID.fromString("fbae5e27-a72a-4193-8fb8-042f60cc7568");

    public Broadsword(Settings settings) {
        super(KeraniteToolMaterial.KERANITE, 13, (0.8f - 4f), settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player){
        stack.getOrCreateNbt().putInt("meter", 0);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_UUID, "Weapon Modifier", 1d, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_UUID, "Weapon Modifier", 1.5d, EntityAttributeModifier.Operation.ADDITION));
        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot);
    }

    public TypedActionResult<ItemStack> conduct(PlayerEntity user, Hand hand){
        NbtCompound tag = user.getStackInHand(hand).getOrCreateNbt();
        int maxmeter = 10;
        if(!tag.getBoolean("charged") &&tag.getInt("meter")>=maxmeter) {
            tag.putBoolean("charged", true);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }else{
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }

    public TypedActionResult<ItemStack> shockwave(World world, PlayerEntity user, Hand hand){
        user.setVelocity(new Vec3d(0,0.75,0));
        user.fallDistance = -5;
        if(world instanceof ServerWorld serverWorld){
            SpecialUtil.createShockwave(serverWorld, user.getPos(), 5, 3, 5, user);
        }
        user.getItemCooldownManager().set(this, 80);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    public TypedActionResult<ItemStack> magnet(World world, PlayerEntity user, Hand hand){
        if(world instanceof ServerWorld serverWorld){
            SpecialUtil.createShockwave(serverWorld, user.getPos(), 5, 0, -5, user);
        }
        user.getItemCooldownManager().set(this, 80);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    public TypedActionResult<ItemStack> backdash(World world, PlayerEntity user, Hand hand){
        user.setVelocity(user.getRotationVector().multiply(new Vec3d(-1, 0, -1)));
        user.addVelocity(0,0.2,0);
        user.fallDistance = -3;

        if(world instanceof ServerWorld serverWorld) {
            serverWorld.playSoundFromEntity(null, user, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS, 0.6f, 0.8f);
            serverWorld.spawnParticles(ParticleTypes.CLOUD.getType(), user.getX(), user.getY(), user.getZ(), 5, 0,0,0,0.01);
        }

        user.getItemCooldownManager().set(this, 30);
        return TypedActionResult.consume(user.getStackInHand(hand));}

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        boolean hasConduct = EnchantmentHelper.getLevel(ModEnchantments.CONDUCTOR, user.getStackInHand(hand)) > 0;
        boolean hasShockwave = EnchantmentHelper.getLevel(ModEnchantments.SHOCKWAVE, user.getStackInHand(hand)) > 0;
        boolean hasMagnetized = EnchantmentHelper.getLevel(ModEnchantments.MAGNETIZED, user.getStackInHand(hand)) > 0;
        if (hasConduct) {
            return conduct(user,hand);
        } else if(hasShockwave) {
            return shockwave(world, user, hand);
        } else if(hasMagnetized) {
            return magnet(world,user,hand);
        }
        else {
            return backdash(world, user, hand);
        }
    }
}