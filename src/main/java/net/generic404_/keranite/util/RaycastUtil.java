package net.generic404_.keranite.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RaycastUtil {
    public static EntityHitResult entityTarget(LivingEntity player, double MaxDistance){
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

//    public static HitResult raycastInDirection(MinecraftClient client, Vec3d rotation, float distance, boolean includeFluids){
//        Entity cament = client.cameraEntity;
//        return cament != null ? cament.raycast(distance, 1f, includeFluids) : null;
//    }
//
//    public static boolean entityInSight(Entity entity1, Entity entity2){
//        boolean Insight = false;
//        if()
//        return Insight;
//    }
}
