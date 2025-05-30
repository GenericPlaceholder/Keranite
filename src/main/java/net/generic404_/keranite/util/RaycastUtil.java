package net.generic404_.keranite.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RaycastUtil {
    public static EntityHitResult raycastEntities(PlayerEntity player, double MaxDistance){
        World world = player.getWorld();
        Vec3d cameraPos = player.getCameraPosVec(1);
        Vec3d lookVec = player.getRotationVec(1);
        Vec3d reachVec = cameraPos.add(lookVec.multiply(MaxDistance));
        Box box = player.getBoundingBox().stretch(lookVec.multiply(MaxDistance)).expand(1);
        return ProjectileUtil.getEntityCollision(
                world, player, cameraPos, reachVec, box,
                (entity) -> !entity.isSpectator() && entity.isAlive() && entity.isCollidable()
        );
    }
}
