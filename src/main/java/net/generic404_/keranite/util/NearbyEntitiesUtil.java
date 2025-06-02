package net.generic404_.keranite.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;

public class NearbyEntitiesUtil {
    public static ArrayList<Entity> getNearbyEntities(LivingEntity user, int maxDistance, BlockPos position) {
        Box myBox = new Box(position).expand(maxDistance);

        List<Entity> oldEntityList = user.getWorld().getOtherEntities(user, myBox);
        ArrayList<Entity> entityList = new ArrayList<>(List.of());
        for (Entity ent : oldEntityList) {
            if (ent.isAlive() && ent.isLiving() && ent.isAttackable()) {
                entityList.add(ent);
            }
        }
        return(entityList);
    }
}
