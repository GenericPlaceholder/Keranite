package net.generic404_.keranite.enchantment;

import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class Force extends Enchantment {
    public Force() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }
    @Override
    public int getMaxLevel() {
        return 1;
    }
    @Override
    public boolean isAcceptableItem(@NotNull ItemStack stack) {
        return stack.isOf(ModItems.KERANITE_RAPIER)||stack.getItem() instanceof ShovelItem;
    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target.getWorld() instanceof ServerWorld serverWorld){
            DamageSources sources = serverWorld.getDamageSources();

            int speed;
            if(
//                    MathHelper.abs((float) user.getVelocity().x)+MathHelper.abs((float) user.getVelocity().z)
                    MathHelper.sqrt((float)(
                            MathHelper.square(user.getVelocity().x)
                                    +
                                    MathHelper.square(user.getVelocity().y)))
                    >MathHelper.abs((float) user.getVelocity().y)) {
                speed =
                        MathHelper.roundDownToMultiple(
                                MathHelper.sqrt((float) (
                                                MathHelper.square(user.getVelocity().x)
                                                        +
                                                        MathHelper.square(user.getVelocity().y)
                                                        +
                                                        MathHelper.square(user.getVelocity().z)
                                        )
                                ) * 5,
                                1
                        );
            } else {
                speed = MathHelper.roundDownToMultiple(user.fallDistance/2, 1);
            }
            target.timeUntilRegen = 0;
            if(user instanceof PlayerEntity plr) {
                target.damage(sources.playerAttack(plr), speed);
            } else {
                target.damage(sources.mobAttack(user), speed);
            }
            Keranite.LOGGER.info(String.valueOf(speed));
            user.fallDistance = -3;
        }
    }
}
