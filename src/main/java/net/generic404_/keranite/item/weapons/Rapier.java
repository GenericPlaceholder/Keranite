package net.generic404_.keranite.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.generic404_.keranite.item.toolmaterials.KeraniteToolMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class Rapier extends SwordItem {
    protected static final UUID ATTACKREACH_MODIFIER_UUID = UUID.fromString("a92256bc-0efa-47ed-aab1-adea724a852c");
    protected static final UUID REACH_MODIFIER_UUID = UUID.fromString("603cbd44-8360-4df7-b10a-2670a4414694");
    public Rapier(Settings settings) {
        super(KeraniteToolMaterial.KERANITE, 7, (1.3f - 4f), settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACKREACH_MODIFIER_UUID, "Weapon Modifier", 0.5d, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_UUID, "Weapon Modifier", 1d, EntityAttributeModifier.Operation.ADDITION));
        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot);
    }

    // make the actual sword do more damage during elytra flight, i mean attack damage

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!user.isFallFlying()) {
            user.addVelocity(user.getRotationVector().multiply(new Vec3d(1, 1, 1).multiply(1)));
            user.fallDistance = -3;
            user.getItemCooldownManager().set(this, 50);
        }else{
            user.addVelocity(user.getRotationVector().multiply(new Vec3d(1, 1, 1).multiply(1.5)));
            user.getItemCooldownManager().set(this, 100);
        }
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}