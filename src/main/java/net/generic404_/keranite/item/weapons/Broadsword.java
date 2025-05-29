package net.generic404_.keranite.item.weapons;

import net.generic404_.keranite.item.toolmaterials.KeraniteToolMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Broadsword extends SwordItem {
    public Broadsword(Settings settings) {
        super(KeraniteToolMaterial.INSTANCE, 13, (0.8f - 4f), settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        String enchantz = user.getStackInHand(hand).getEnchantments().toString();
        String[] enchants = enchantz.split("\"");
        boolean hasConduct = false;
        boolean hasDischarge = false;
        for (String enchant : enchants) {
            if (enchant.equals("keranite:conductor")) {
                hasConduct = true;
            }
            if (enchant.equals("keranite:discharge")) {
                hasDischarge = true;
            }
        }
        if (hasConduct) {
            // how tf do raycasts work. this is so confusing
            //HitResult Raycast = world.raycast(new RaycastContext(user.getPos(), user.getRotationVector().x*10, user.getRotationVector().y*10, user.getRotationVector().z*10, world.get));
            //Keranite.LOGGER.info(Raycast.toString());
            return TypedActionResult.pass(user.getStackInHand(hand));
        } else if(hasDischarge){
            //double size = 5;
            //Box box = new Box(user.getPos().add(size,size,size),user.getPos().subtract(size,size,size));
            //Keranite.LOGGER.info(world.getEntityCollisions(, box).toString());



            return TypedActionResult.consume(user.getStackInHand(hand));
        } else {
            // there needs to be block detection to prevent phasing.
            // ill figure this out.
            //user.setPosition(user.getPos().subtract(user.getRotationVector().multiply(new Vec3d(1.5, 0, 1.5))));
            user.setVelocity(user.getRotationVector().multiply(new Vec3d(-1, 0, -1)));
            user.getItemCooldownManager().set(this, 30);
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
    }
}