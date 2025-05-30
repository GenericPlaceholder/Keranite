package net.generic404_.keranite.item.usable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class MinorAnchor extends Item {
    public MinorAnchor(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        VoxelShape FLAT_SHAPE = Block.createCuboidShape(0,8,0,16,8.01,16);

        user.getItemCooldownManager().set(this, 200);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            user.getStackInHand(hand).decrement(1);
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
