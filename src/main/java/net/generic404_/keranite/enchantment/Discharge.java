package net.generic404_.keranite.enchantment;

import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.damagetype.ModDamageTypes;
import net.generic404_.keranite.util.RandomUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;

public class Discharge extends Enchantment {

    public Discharge() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinLevel() {
        return 1;
    }
    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        super.onTargetDamaged(user, target, level);

        int distanceFromPlayerMax = 6*level;
        Box myBox = new Box(target.getBlockPos()).expand(distanceFromPlayerMax);
        List<MobEntity> entityList = user.getWorld().getEntitiesByClass(MobEntity.class,myBox, LivingEntity::isAlive);

        ArrayList<MobEntity> newEntityList = new ArrayList<>();
        if(entityList.size()>3){
            for(int i=0;i<3;i++){
                int rand = RandomUtil.getRandom(0,entityList.size()-1);
                newEntityList.add(entityList.get(rand));
                // gotta make it remove the already damaged guy.
                //newEntityList.remove(rand);
            }
        }else{
            newEntityList = (ArrayList<MobEntity>) entityList;
        }
        for(MobEntity viewed : newEntityList){
            viewed.damage(ModDamageTypes.of(user.getWorld(), ModDamageTypes.DISCHARGED), 5);
        }
        Keranite.LOGGER.info(String.valueOf(newEntityList));
    }
}
