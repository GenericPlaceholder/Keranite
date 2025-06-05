package net.generic404_.keranite.item.usable;

import net.generic404_.keranite.projectiles.ConcussionBomb;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ConcussionBombItem extends Item {
    public ConcussionBombItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack bomb = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5F, 1F);
        user.getItemCooldownManager().set(bomb.getItem(), 20);

        if (!world.isClient) {
            ConcussionBomb bombEntity = new ConcussionBomb(world, user);
            bombEntity.setItem(bomb);
            bombEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1F);
            world.spawnEntity(bombEntity); // spawns entity
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            user.getStackInHand(hand).decrement(1);
        }

        return TypedActionResult.success(bomb,world.isClient);
    }
}
