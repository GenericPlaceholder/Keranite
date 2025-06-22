package net.generic404_.keranite.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.generic404_.keranite.enchantment.ModEnchantments;
import net.generic404_.keranite.entity.JavelinEntity;
import net.generic404_.keranite.item.toolmaterials.KeraniteToolMaterial;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.UUID;

public class Javelin extends SwordItem {
    protected static final UUID ATTACKREACH_MODIFIER_UUID = UUID.fromString("a92256bc-0efa-47ed-aab1-adea724a852c");
    protected static final UUID REACH_MODIFIER_UUID = UUID.fromString("603cbd44-8360-4df7-b10a-2670a4414694");
    public Javelin(Settings settings) {
        super(KeraniteToolMaterial.KERANITE, 10, (1.1f - 4f), settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACKREACH_MODIFIER_UUID, "Weapon Modifier", 0.8d, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_UUID, "Weapon Modifier", 1.25d, EntityAttributeModifier.Operation.ADDITION));
        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            if (i >= 10) {
                int j = EnchantmentHelper.getLevel(ModEnchantments.LUNGE, stack);

                // normally this would have a "if on ground or j <= 0" but i want it to always work
                if (!world.isClient) {
                    stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(user.getActiveHand()));
                    if (j == 0) {
                        JavelinEntity javelinEntity = new JavelinEntity(world, playerEntity, stack);
                        javelinEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 2.5F + j * 0.5F, 1.0F);
                        if (playerEntity.getAbilities().creativeMode) {
                            javelinEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        world.spawnEntity(javelinEntity);
                        world.playSoundFromEntity(null, javelinEntity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        if (!playerEntity.getAbilities().creativeMode) {
                            playerEntity.getInventory().removeOne(stack);
                        }
                    }
                }

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                if (j > 0) {
                    if(!Objects.equals(playerEntity.getVelocity(), new Vec3d(0, 0, 0))){
                        playerEntity.setVelocity(playerEntity.getVelocity().multiply(0.5));
                    }
                    playerEntity.addVelocity(playerEntity.getRotationVector().multiply(2,1.5,2));
                    playerEntity.useRiptide(10);
                    if (playerEntity.isOnGround()) {
                        playerEntity.addVelocity(0, 0.2, 0);
                    }

                    SoundEvent soundEvent;
                    if (j >= 3) {
                        soundEvent = SoundEvents.ITEM_TRIDENT_RIPTIDE_3;
                    } else if (j == 2) {
                        soundEvent = SoundEvents.ITEM_TRIDENT_RIPTIDE_2;
                    } else {
                        soundEvent = SoundEvents.ITEM_TRIDENT_RIPTIDE_1;
                    }

                    world.playSoundFromEntity(null, playerEntity, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        } else if (EnchantmentHelper.getLevel(ModEnchantments.LUNGE,itemStack) > 0 && !user.isOnGround()) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }
}