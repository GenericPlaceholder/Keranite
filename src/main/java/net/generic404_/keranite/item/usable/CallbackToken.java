package net.generic404_.keranite.item.usable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CallbackToken extends Item {
    public CallbackToken(Settings settings) {
        super(settings);
    }

    // callback variables
    Vec3d savedpos = null;
    Vec3d savedvelo = null;
    float savedyaw = 0;
    float savedpitch = 0;

    // this is awful.
    // make it nbt based or smth
    // also, consider making it only save position.
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        if (world.isClient) {
            if (savedpos == null) {
                user.sendMessage(Text.of("[ Position Saved ]"));
                savedpos = user.getPos();
                savedyaw = user.getYaw();
                savedpitch = user.getPitch();
                savedvelo = user.getVelocity();
            } else {
                if(user.squaredDistanceTo(savedpos)<2500) {
                    user.sendMessage(Text.of("[ Position Loaded ]"));
                    user.setPosition(savedpos);
                    user.setYaw(savedyaw);
                    user.setPitch(savedpitch);
                    user.setVelocity(savedvelo);
                } else {
                    user.sendMessage(Text.of("[ Saved Position Out Of Range ]"));
                }
                savedpos = null;
                user.incrementStat(Stats.USED.getOrCreateStat(this));
                if (!user.getAbilities().creativeMode) {
                    user.getStackInHand(hand).decrement(1);
                }
            }
            user.getItemCooldownManager().set(this, 20);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
