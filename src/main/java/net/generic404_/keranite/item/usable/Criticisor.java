package net.generic404_.keranite.item.usable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Criticisor extends Item {
    public Criticisor(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 200);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            user.getStackInHand(hand).decrement(1);
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
