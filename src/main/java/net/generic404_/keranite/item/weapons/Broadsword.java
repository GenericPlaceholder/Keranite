package net.generic404_.keranite.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.generic404_.keranite.effect.ModEffects;
import net.generic404_.keranite.item.toolmaterials.KeraniteToolMaterial;
import net.generic404_.keranite.util.RaycastUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class Broadsword extends ToolItem {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    protected static final UUID ATTACKREACH_MODIFIER_UUID = UUID.fromString("1293b11e-094f-4e9b-8f20-4fcbf0cdae25");
    protected static final UUID REACH_MODIFIER_UUID = UUID.fromString("fbae5e27-a72a-4193-8fb8-042f60cc7568");
    public Broadsword(Settings settings) {
        super(KeraniteToolMaterial.INSTANCE, settings);
//        super(KeraniteToolMaterial.INSTANCE, 12, (0.8f - 4f), settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(UUID.randomUUID(), "Weapon Modifier", 13D, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(UUID.randomUUID(),"Weapon Modifier", -3.2D, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACKREACH_MODIFIER_UUID, "Weapon Modifier", 1.25d, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_UUID, "Weapon Modifier", 2d, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
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
            EntityHitResult raycat = RaycastUtil.raycastEntities(user, 5d);
            if(raycat!=null) {
                LivingEntity entity = (LivingEntity) raycat.getEntity();
                entity.addStatusEffect(new StatusEffectInstance(ModEffects.SHOCKED, 60, 2, false, false, false));
                entity.addStatusEffect(new StatusEffectInstance(ModEffects.CHARGED, 300, 0, false, true, true));
            }
            return TypedActionResult.consume(user.getStackInHand(hand));
        } else {
            user.setVelocity(user.getRotationVector().multiply(new Vec3d(-1, 0, -1)));
            user.addVelocity(0,0.2,0);
            user.fallDistance = -3;
            user.getItemCooldownManager().set(this, 30);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
    }
}