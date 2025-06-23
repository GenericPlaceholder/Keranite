package net.generic404_.keranite.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MiscUtil {
    static public boolean isPositionInConeByEndpoint(Vec3d origin, Vec3d endpoint, Vec3d position, float aperture){

        // This is for our convenience
        float halfAperture = aperture/2.f;

        // Vector pointing to X point from apex
        Vec3d apexToXVect = origin.subtract(position);

        // Vector pointing from apex to circle-center point.
        Vec3d axisVect = origin.subtract(endpoint);

        // X is lying in cone only if it's lying in
        // infinite version of its cone -- that is,
        // not limited by "round basement".
        // We'll use dotProd() to
        // determine angle between apexToXVect and axis.
        boolean isInInfiniteCone = apexToXVect.dotProduct(axisVect)
                /apexToXVect.length()/axisVect.length()
                >
                // We can safely compare cos() of angles
                // between vectors instead of bare angles.
                Math.cos(halfAperture);


        if(!isInInfiniteCone) return false;

        // X is contained in cone only if projection of apexToXVect to axis
        // is shorter than axis.
        // We'll use dotProd() to figure projection length.
        return (apexToXVect.dotProduct(axisVect))
                /(axisVect.length())
                <
                axisVect.length();
    }

    static public boolean isPositionInConeByRotation(Vec3d origin, Vec3d rotationVector, float maxDistance, Vec3d position, float aperture){
        Vec3d endpoint = origin.add(rotationVector.multiply(maxDistance).multiply(new Vec3d(1,1,-1).multiply(-1)));

        // This is for our convenience
        float halfAperture = aperture/2.f;

        // Vector pointing to X point from apex
        Vec3d apexToXVect = origin.subtract(position);

        // Vector pointing from apex to circle-center point.
        Vec3d axisVect = origin.subtract(endpoint);

        // X is lying in cone only if it's lying in
        // infinite version of its cone -- that is,
        // not limited by "round basement".
        // We'll use dotProd() to
        // determine angle between apexToXVect and axis.
        boolean isInInfiniteCone = apexToXVect.dotProduct(axisVect)
                /apexToXVect.length()/axisVect.length()
                >
                // We can safely compare cos() of angles
                // between vectors instead of bare angles.
                Math.cos(halfAperture);


        if(!isInInfiniteCone) return false;

        // X is contained in cone only if projection of apexToXVect to axis
        // is shorter than axis.
        // We'll use dotProd() to figure projection length.
        return (apexToXVect.dotProduct(axisVect))
                /(axisVect.length())
                <
                axisVect.length();
    }

    static public float degreesToRadians(float degrees){
        return (float) (degrees * (Math.PI/180));
    }

    static public float radiansToDegrees(float radians){
        return (float) (radians * (180/Math.PI));
    }

    static public BlockPos Vec3dToBlockPos(Vec3d vec3d){
        return new BlockPos(MathHelper.roundDownToMultiple(vec3d.x,1),MathHelper.roundDownToMultiple(vec3d.y,1),MathHelper.roundDownToMultiple(vec3d.z,1));
    }

    static public Vec3d getEntityCenterPos(Entity target){
        return target.getPos().add(0,target.getHeight(),0);
    }
}
