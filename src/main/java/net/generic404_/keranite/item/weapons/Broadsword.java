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

public class Broadsword extends SwordItem {
    public Broadsword(Settings settings) {
        super(KeraniteToolMaterial.INSTANCE, 14, -3.2F, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient){
            // this is NOT working. lmao. maybe do callback instead?
            user.setPosition(Vec3d.of(user.getMovementDirection().getVector()).multiply(5));
        }
        user.getItemCooldownManager().set(this, 10);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
