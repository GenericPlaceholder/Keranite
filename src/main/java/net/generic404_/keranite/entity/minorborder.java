package net.generic404_.keranite.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class minorborder extends Entity {
    public minorborder(EntityType<?> type, World world) {
        super(type, world);
        this.noClip = true;
        this.setInvisible(true);
        double size = 10;
        this.setBoundingBox(new Box(new Vec3d(1,1,1).multiply(size), new Vec3d(1,1,1).multiply(size)));
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
