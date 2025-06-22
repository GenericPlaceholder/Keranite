package net.generic404_.keranite.util;

import net.generic404_.keranite.damagetype.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;

public class SpecialUtil {
    public static void createShockwave(World world, BlockPos position, int maxDistance, int damage, int knockback){
        ArrayList<Entity> entityList = NearbyUtil.getByWorld(world,maxDistance, position);
        for(Entity ent : entityList){
            ent.damage(ModDamageTypes.of(world, ModDamageTypes.SHOCKWAVE), damage);
            ent.setOnGround(false);
            ent.addVelocity(new Vec3d(0,0.05*knockback,0));
            ent.addVelocity(((ent.getPos().subtract(position.toCenterPos()))
                    .multiply(position.toCenterPos().distanceTo(ent.getPos())-maxDistance))
                    .multiply((double) knockback /-40));
        }
    }
}
