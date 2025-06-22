package net.generic404_.keranite.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class NearbyUtil {
    // TODO: Clean this up at some point. It's too messy.
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

    public static ArrayList<Entity> getByWorld(World world, float maxDistance, BlockPos position) {
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

    public static ArrayList<World> getWorlds(LivingEntity user, float maxDistance, BlockPos position) {
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

    public static ArrayList<Entity> sortByNearestEntities(Vec3d position, ArrayList<Entity> entities, int maxAmount, float maxDistance){
        ArrayList<Entity> closestEntities = new ArrayList<>();
        for(Entity entity : entities){
            if(closestEntities.size()<maxAmount&&entity.getPos().distanceTo(position)<=maxDistance){
                closestEntities.add(entity);
            } else {
                int counter0 = 0;
                for (Entity entity1 : closestEntities) {
                    if (entity.getPos().distanceTo(position)<entity1.getPos().distanceTo(position)&&entity.getPos().distanceTo(position)<=maxDistance) {
                        closestEntities.set(counter0, entity);
                    }
                    counter0++;
                }
            }
        }
        return closestEntities;
    }

    public static ArrayList<Entity> getNearestEntitiesInCone(World world, Vec3d origin, Vec3d rotationVector, float maxDistance, float degrees, int maxAmount){
        ArrayList<Entity> entities = NearbyUtil.getByWorld(world,maxDistance,MiscUtil.Vec3dToBlockPos(origin));
        entities = NearbyUtil.sortByNearestEntities(origin, entities, maxAmount, maxDistance);
        ArrayList<Entity> filteredEntities = new ArrayList<>();
        for(Entity entity : entities){
            if(MiscUtil.isPositionInConeByRotation(origin, rotationVector, maxDistance, entity.getPos(), MiscUtil.degreesToRadians(degrees))){
                if(filteredEntities.size()<maxAmount){
                    filteredEntities.add(entity);
                }else{
                    int counter0 = 0;
                    for (Entity entity1 : filteredEntities) {
                        if (entity.getPos().distanceTo(origin)<entity1.getPos().distanceTo(origin)&&entity.getPos().distanceTo(origin)<=maxDistance) {
                            filteredEntities.set(counter0, entity);
                        }
                        counter0++;
                    }
                }
            }
        }
        return filteredEntities;
    }

    public static ArrayList<Entity> getNearestEntitiesInConeExcept(World world, Vec3d origin, Vec3d rotationVector, float maxDistance, float degrees, int maxAmount, ArrayList<Entity> exceptions){
        ArrayList<Entity> entities = NearbyUtil.getByWorld(world,maxDistance,MiscUtil.Vec3dToBlockPos(origin));
        if(entities.isEmpty()){return new ArrayList<>();}
        ArrayList<Entity> entities1 = entities;
        // TODO: Fix ConcurrentModificationException in line below. No clue what is happening.
        for(Entity entity : entities1){
            for(Entity entity1 : exceptions){
                if(entity==entity1){
                    entities.remove(entity);
                }
            }
        }
        entities = NearbyUtil.sortByNearestEntities(origin, entities, maxAmount, maxDistance);
        ArrayList<Entity> filteredEntities = new ArrayList<>();
        for(Entity entity : entities){
            if(MiscUtil.isPositionInConeByRotation(origin, rotationVector, maxDistance, entity.getPos(), MiscUtil.degreesToRadians(degrees))){
                if(filteredEntities.size()<maxAmount){
                    filteredEntities.add(entity);
                }else{
                    int counter0 = 0;
                    for (Entity entity1 : filteredEntities) {
                        if (entity.getPos().distanceTo(origin)<entity1.getPos().distanceTo(origin)&&entity.getPos().distanceTo(origin)<=maxDistance) {
                            filteredEntities.set(counter0, entity);
                        }
                        counter0++;
                    }
                }
            }
        }
        return filteredEntities;
    }
}
