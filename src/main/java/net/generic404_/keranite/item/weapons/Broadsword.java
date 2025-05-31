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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class Broadsword extends SwordItem {
    protected static final UUID ATTACKREACH_MODIFIER_UUID = UUID.fromString("1293b11e-094f-4e9b-8f20-4fcbf0cdae25");
    protected static final UUID REACH_MODIFIER_UUID = UUID.fromString("fbae5e27-a72a-4193-8fb8-042f60cc7568");

    public Broadsword(Settings settings) {
        super(KeraniteToolMaterial.INSTANCE, 12, (0.8f - 4f), settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player){
        stack.getOrCreateNbt().putInt("meter", 0);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACKREACH_MODIFIER_UUID, "Weapon Modifier", 1d, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_UUID, "Weapon Modifier", 1.5d, EntityAttributeModifier.Operation.ADDITION));
        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        String enchantz = user.getStackInHand(hand).getEnchantments().toString();
        String[] enchants = enchantz.split("\"");
        boolean hasConduct = false;
        for (String enchant : enchants) {
            if (enchant.equals("keranite:conductor")) {
                hasConduct = true;
            }
        }
        if (hasConduct) {
            NbtCompound tag = user.getStackInHand(hand).getOrCreateNbt();
            int maxmeter = 20;
            if(!tag.getBoolean("charged") &&tag.getInt("meter")>=maxmeter) {
                tag.putBoolean("charged", true);
//            EntityHitResult raycat = RaycastUtil.raycastEntities(user, 5d);
//            if(raycat!=null) {
//                LivingEntity entity = (LivingEntity) raycat.getEntity();
//                entity.addStatusEffect(new StatusEffectInstance(ModEffects.SHOCKED, 60, 2, false, false, false));
//                entity.addStatusEffect(new StatusEffectInstance(ModEffects.CHARGED, 300, 0, false, true, true));
//            }
                return TypedActionResult.consume(user.getStackInHand(hand));
            }else{
                return TypedActionResult.pass(user.getStackInHand(hand));
            }
        } else {
            user.setVelocity(user.getRotationVector().multiply(new Vec3d(-1, 0, -1)));
            user.addVelocity(0,0.2,0);
            user.fallDistance = -3;
            user.getItemCooldownManager().set(this, 30);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
    }
}