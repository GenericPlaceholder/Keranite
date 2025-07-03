package net.generic404_.keranite.util;

import net.generic404_.keranite.damagetype.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;

public class SpecialUtil {
    public static void createShockwave(World world, Vec3d position, int maxDistance, int damage, double knockback){
        BlockPos blockPos = MiscUtil.Vec3dToBlockPos(position);
        ArrayList<Entity> entityList = NearbyUtil.getByWorld(world,maxDistance, blockPos);
        for(Entity ent : entityList){
            ent.damage(ModDamageTypes.of(world, ModDamageTypes.SHOCKWAVE), damage);
            ent.setOnGround(false);
            ent.addVelocity(new Vec3d(0,0.05*knockback,0));
            ent.addVelocity(((ent.getPos().subtract(position))
                    .multiply((maxDistance+0.5)-position.distanceTo(ent.getPos())))
                    .multiply((double) knockback /40));
        }
        if(world instanceof ServerWorld serverWorld){
            serverWorld.playSound(null, blockPos, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS, 1f, 0.8f);
            serverWorld.playSound(null, blockPos, SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.PLAYERS, 0.8f, 1f);
            serverWorld.playSound(null, blockPos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.6f, 1.4f);
            serverWorld.spawnParticles(ParticleTypes.EXPLOSION.getType(), position.getX(), position.getY(), position.getZ(), 10, 0, 0, 0, 0);
            serverWorld.spawnParticles(ParticleTypes.ASH.getType(), position.getX(), position.getY(), position.getZ(), 100, 5, 3, 5, 0.2);
        }
    }
}
