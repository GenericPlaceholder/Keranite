package net.generic404_.keranite.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Map;

public class KeraniteArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP = (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
            .put(KeraniteArmorMaterials.LIGHT, new StatusEffectInstance(StatusEffects.SLOWNESS,2,0,false,false,false))
            .put(KeraniteArmorMaterials.HEAVY, new StatusEffectInstance(StatusEffects.SLOWNESS,2,1,false,false,false)).build();

    public KeraniteArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected){
        if(!world.isClient()){
            if(entity instanceof PlayerEntity player){
                if(hasFullSuitOfArmorOn(player)&&!(correctArmorPercentage(KeraniteArmorMaterials.HEAVY, player)[1]>0)){
                    evaluateArmorEffects(player);
                }else{
                    int[] armorPercent = correctArmorPercentage(KeraniteArmorMaterials.HEAVY, player);
                    if(armorPercent[2]==4){
                        if(armorPercent[1]>2){
                            addStatusEffect(player, new StatusEffectInstance(StatusEffects.SLOWNESS,2,1,false,false,false));
                        }else{
                            addStatusEffect(player, new StatusEffectInstance(StatusEffects.SLOWNESS,2,0,false,false,false));
                        }
                    }else {
                        if (armorPercent[1] <= 3 && armorPercent[1] > 0) {
                            addStatusEffect(player, new StatusEffectInstance(StatusEffects.SLOWNESS, 2, 0, false, false, false));
                        }
                    }
                }
            }
        }
        super.inventoryTick(stack,world,entity,slot,selected);
    }

    private void evaluateArmorEffects(PlayerEntity player){
        for(Map.Entry<ArmorMaterial,StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()){
            ArmorMaterial mapArmorMaterial = entry.getKey();
            StatusEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)){
                addStatusEffectForMaterial(player,mapArmorMaterial,mapStatusEffect);
            }
        }
    }

    private void addStatusEffect(PlayerEntity player, StatusEffectInstance effect){
        boolean hasPlayerEffect = player.hasStatusEffect(effect.getEffectType());
        if (!hasPlayerEffect) {
            player.addStatusEffect(effect);
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapStatusEffect){
        boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

        if(hasCorrectArmorOn(mapArmorMaterial, player)&&!hasPlayerEffect){
            player.addStatusEffect(new StatusEffectInstance(mapStatusEffect));
        }
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty()&&!chestplate.isEmpty()&&!leggings.isEmpty()&&!boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        for(ItemStack armorStack : player.getInventory().armor){
            if(!(armorStack.getItem() instanceof ArmorItem)){
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
        ArmorItem chestplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial()==material&&chestplate.getMaterial()==material&&leggings.getMaterial()==material&&boots.getMaterial()==material;
    }

    private int[] correctArmorPercentage(ArmorMaterial material, PlayerEntity player){
        int amount = 0;
        int max = 0;
        for(ItemStack armorStack : player.getInventory().armor){
            if(armorStack.getItem() instanceof ArmorItem){
                max+=1;
                if(((ArmorItem)armorStack.getItem()).getMaterial()==material){amount+=1;}
            }
        }
        return new int[]{max-amount, amount, max};
    }
}
