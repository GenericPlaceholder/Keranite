package net.generic404_.keranite.item.weapons;

import net.generic404_.keranite.item.toolmaterials.KeraniteToolMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class Broadsword extends SwordItem {
    // i cant import a silly thing. this is here until i figure it out
    //protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("");


    public Broadsword(Settings settings) {
        super(KeraniteToolMaterial.INSTANCE, 13, (0.8f - 4f), settings);
    }

    // callback variables
    Vec3d savedpos = null;
    Vec3d savedvelo = null;
    float savedyaw = 0;
    float savedpitch = 0;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        String enchantz = user.getStackInHand(hand).getEnchantments().toString();
        String[] enchants = enchantz.split("\"");
        boolean hasConduct = false;
        boolean hasCallback = false;
        for (String enchant : enchants) {
            if (enchant.equals("keranite:conductor")) {
                hasConduct = true;
            }
            if (enchant.equals("keranite:callback")) {
                hasCallback = true;
            }
        }
        if (hasConduct) {
            // change this to a raycast eventually.
            return TypedActionResult.pass(user.getStackInHand(hand));
        } else if (hasCallback) {
            if (world.isClient) {
                if (savedpos == null) {
                    user.sendMessage(Text.of("[ Position Saved ]"));
                    savedpos = user.getPos();
                    savedyaw = user.getYaw();
                    savedpitch = user.getPitch();
                    savedvelo = user.getVelocity();
                } else {
                    user.sendMessage(Text.of("[ Position Loaded ]"));
                    user.setPosition(savedpos);
                    user.setYaw(savedyaw);
                    user.setPitch(savedpitch);
                    user.setVelocity(savedvelo);
                    savedpos = null;
                }
                user.getItemCooldownManager().set(this, 60);
                return TypedActionResult.consume(user.getStackInHand(hand));
            }
        } else {
            // there needs to be block detection to prevent phasing.
            // ill figure this out.
            user.setPosition(
                    user.getPos()
                            .subtract(
                                    user.getRotationVector()
                                            .multiply(new Vec3d(1.5, 0, 1.5))));
            user.getItemCooldownManager().set(this, 30);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
        return TypedActionResult.fail(user.getStackInHand(hand));
    }
}
