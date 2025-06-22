package net.generic404_.keranite.enchantment;

import net.generic404_.keranite.damagetype.ModDamageTypes;
import net.generic404_.keranite.item.ModItems;
import net.generic404_.keranite.util.NearbyUtil;
import net.generic404_.keranite.util.RandomUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Discharge extends Enchantment {

    public Discharge() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
    @Override
    public int getMaxLevel() {
        return 3;
    }
    @Override
    public boolean isAcceptableItem(@NotNull ItemStack stack) {
        return stack.isOf(ModItems.BROADSWORD)||stack.isOf(ModItems.RAPIER);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        super.onTargetDamaged(user, target, level);

        World world = user.getWorld();

//        int distanceFromPlayerMax = 6*level;
//        Box myBox = new Box(target.getBlockPos()).expand(distanceFromPlayerMax);
//
//        List<Entity> oldEntityList = user.getWorld().getOtherEntities(target,myBox);
//        ArrayList<Entity> entityList = new ArrayList<>(List.of());
//        for(Entity ent : oldEntityList){
//            if(ent.isAlive()&&ent.isLiving()&&ent.isAttackable()){entityList.add(ent);}
//        }

        ArrayList<Entity> entityList = NearbyUtil.getByLivingEntity(user,6*level,target.getBlockPos());

        ArrayList<Entity> newEntityList = new ArrayList<>();
        if(entityList.size()>3){
            for(int i=0;i<3;i++){
                int rand = RandomUtil.getRandomInt(0,entityList.size()-1);
                newEntityList.add(entityList.get(rand));
                //keeps saying length 1. what????
                //newEntityList.remove(rand);
            }
        }else{
            newEntityList = entityList;
        }
        for(Entity viewed : newEntityList){
            for(int i=0;i<10;i++) {
                world.addParticle(ParticleTypes.ELECTRIC_SPARK, viewed.getX() + RandomUtil.getRandomFloat(-0.5f, 0.5f), viewed.getY() + RandomUtil.getRandomFloat(-0.5f, 0.5f), viewed.getZ() + RandomUtil.getRandomFloat(-0.5f, 0.5f), 0, 0, 0);
            }
            viewed.damage(ModDamageTypes.of(user.getWorld(), ModDamageTypes.DISCHARGED), 5*level);
        }
    }
}
