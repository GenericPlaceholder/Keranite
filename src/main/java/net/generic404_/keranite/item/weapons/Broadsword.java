package net.generic404_.keranite.item.weapons;

import net.generic404_.keranite.item.toolmaterials.KeraniteToolMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Broadsword extends SwordItem {
    public Broadsword(Settings settings) {
        super(KeraniteToolMaterial.INSTANCE, 12, -3.3F, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient){
            user.sendMessage(Text.literal("Used a Keranite Broadsword. For now, just imagine the cool effects."));
        }
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
