package net.generic404_.keranite.entity;

import net.generic404_.keranite.enchantment.ModEnchantments;
import net.generic404_.keranite.item.ModItems;
import net.generic404_.keranite.util.MiscUtil;
import net.generic404_.keranite.util.NearbyUtil;
import net.generic404_.keranite.util.SpecialUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Objects;

public class JavelinEntity extends TridentEntity {
    private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(JavelinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private ItemStack javelinStack = new ItemStack(ModItems.JAVELIN);
    private boolean dealtDamage;

    public JavelinEntity(EntityType<? extends JavelinEntity> entityType, World world) {
        super(entityType, world);
    }

    public JavelinEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);
        this.javelinStack = stack.copy();
//        this.dataTracker.set(LOYALTY, (byte) EnchantmentHelper.getLoyalty(stack));
        this.dataTracker.set(ENCHANTED, stack.hasGlint());
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ENCHANTED, false);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        if ((this.dealtDamage || this.isNoClip()) && entity != null) {
            if (!this.isOwnerAlive()) {
                if (!this.getWorld().isClient && this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoClip(true);
                Vec3d vec3d = entity.getEyePos().subtract(this.getPos());
                this.setPos(this.getX(), this.getY() + vec3d.y * 0.045, this.getZ());
                if (this.getWorld().isClient) {
                    this.lastRenderY = this.getY();
                }

                double d = 0.10;
                this.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(d)));
                if (this.returnTimer == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
                }

                this.returnTimer++;
            }
        }

        // Disabled "aim assist" code for when I make the Gust enchant work with this. Causes a crash when throwing the Javelin.
        if(!this.inGround&&!dealtDamage&&EnchantmentHelper.getLevel(ModEnchantments.GUST,javelinStack)>0){
            ArrayList<Entity> except = new ArrayList<>();
            except.add(this);
            except.add(this.getOwner());
            ArrayList<Entity> possibleTarget = NearbyUtil.getNearestEntitiesInConeExcept(getWorld(), this.getPos(), this.getRotationVector(), 50, 40, 1, except);
            if(!possibleTarget.isEmpty()) {
                Entity target = possibleTarget.get(0);
                Vec3d rotation = MiscUtil.getEntityCenterPos(target).relativize(this.getPos()).normalize();
                this.setRotation((float) this.getRotationVector().lerp(rotation, 0.2).x, (float) this.getRotationVector().lerp(rotation, 0.2).y);
                this.setVelocity(rotation.multiply(-2));
            }
        }

        super.tick();
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
//        this.discard();
        boolean hasShockwave = EnchantmentHelper.getLevel(ModEnchantments.SHOCKWAVE, javelinStack)>0;
        if(hasShockwave){
            SpecialUtil.createShockwave(getWorld(), blockHitResult.getBlockPos().toCenterPos(), 4, 2,4.5);
        }
        boolean hasMagnet = EnchantmentHelper.getLevel(ModEnchantments.MAGNETIZED, javelinStack)>0;
        if(hasMagnet){
            SpecialUtil.createShockwave(getWorld(), blockHitResult.getBlockPos().toCenterPos(), 4, 2,-3);
        }
        super.onBlockHit(blockHitResult);
    }

    @Override

    protected void onEntityHit(EntityHitResult entityHitResult) {
//        this.discard();
        boolean hasShockwave = EnchantmentHelper.getLevel(ModEnchantments.SHOCKWAVE, javelinStack)>0;
        if(hasShockwave){
            SpecialUtil.createShockwave(getWorld(), entityHitResult.getEntity().getPos(), 4, 2,3);
        }
        boolean hasMagnet = EnchantmentHelper.getLevel(ModEnchantments.MAGNETIZED, javelinStack)>0;
        if(hasMagnet){
            SpecialUtil.createShockwave(getWorld(), entityHitResult.getEntity().getPos(), 4, 2,-3);
        }
        boolean hasGust = EnchantmentHelper.getLevel(ModEnchantments.GUST, javelinStack)>0;
        if(hasGust){
            entityHitResult.getEntity().addVelocity(Objects.requireNonNull(this.getOwner()).getRotationVector().multiply(3));
        }
        super.onEntityHit(entityHitResult);
        dealtDamage = true;
    }

    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() && (!(entity instanceof ServerPlayerEntity) || !entity.isSpectator());
    }
}
