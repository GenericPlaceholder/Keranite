package net.generic404_.keranite.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class NearbyUtil {
    public static ArrayList<Entity> getByLivingEntity(LivingEntity user, int maxDistance, BlockPos position) {
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

    public static ArrayList<Entity> getByWorld(World world, int maxDistance, BlockPos position) {
        Box myBox = new Box(position).expand(maxDistance);

        List<Entity> oldEntityList = world.getOtherEntities(null, myBox);
        ArrayList<Entity> entityList = new ArrayList<>(List.of());
        for (Entity ent : oldEntityList) {
            if (ent.isAlive() && ent.isLiving() && ent.isAttackable()) {
                entityList.add(ent);
            }
        }
        return(entityList);
    }

    public static ArrayList<World> getWorlds(LivingEntity user, int maxDistance, BlockPos position) {
        Box myBox = new Box(position).expand(maxDistance);

        List<Entity> oldEntityList = user.getWorld().getOtherEntities(user, myBox);
        ArrayList<Entity> entityList = new ArrayList<>(List.of());
        for (Entity ent : oldEntityList) {
            if (ent.isAlive() && ent.isLiving()) {
                entityList.add(ent);
            }
        }
        ArrayList<World> worldList = new ArrayList<>(List.of());
        for (Entity ent : entityList) {
            if(ent instanceof PlayerEntity plr) {
                worldList.add(plr.getWorld());
            }
        }
        return(worldList);
    }
}
