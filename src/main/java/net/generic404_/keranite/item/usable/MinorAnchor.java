package net.generic404_.keranite.item.usable;

import net.generic404_.keranite.world.Border;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;

public class MinorAnchor extends Item {
    public MinorAnchor(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // should make a new world border around the activation spot
        //if(!world.isClient()){
            WorldBorder border = new Border();
            border.setCenter(user.getX(), user.getZ());
            border.setDamagePerBlock(0);
            border.setSize(10d);
            border.setWarningBlocks(0);
            border.setWarningTime(0);
            border.tick();
        //}
        user.getItemCooldownManager().set(this, 200);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            user.getStackInHand(hand).decrement(1);
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
