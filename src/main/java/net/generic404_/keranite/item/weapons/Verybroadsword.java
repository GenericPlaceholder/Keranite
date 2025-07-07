package net.generic404_.keranite.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.generic404_.keranite.util.SpecialUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Verybroadsword extends Broadsword {
    public Verybroadsword(Settings settings) {
        super(settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_UUID, "Weapon Modifier", 2d, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_UUID, "Weapon Modifier", 3d, EntityAttributeModifier.Operation.ADDITION));
        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot);
    }

    @Override
    public TypedActionResult<ItemStack> shockwave(World world, PlayerEntity user, Hand hand){
        user.setVelocity(new Vec3d(0,1.25,0));
        user.fallDistance = -10;
        if(world instanceof ServerWorld serverWorld){
            SpecialUtil.createShockwave(serverWorld, user.getPos(), 10, 10, 10, user);
        }
        user.getItemCooldownManager().set(this, 80);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public TypedActionResult<ItemStack> magnet(World world, PlayerEntity user, Hand hand){
        if(world instanceof ServerWorld serverWorld){
            SpecialUtil.createShockwave(serverWorld, user.getPos(), 10, 0, -10, user);
        }
        user.getItemCooldownManager().set(this, 80);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
