package net.generic404_.keranite.item.usable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class DisposableCallbackToken extends Item {
    public DisposableCallbackToken(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack token = user.getStackInHand(hand);
            if (token.getNbt()!=null&&!token.getNbt().getBoolean("positionSaved")) {
                if(world.isClient) {
                    user.sendMessage(Text.of("[ Position Saved ]"));
                }
                token.getOrCreateNbt().putBoolean("positionSaved",true);
                token.getOrCreateSubNbt("pos");
                NbtCompound pos = token.getOrCreateNbt().getCompound("pos");
                pos.putDouble("x",user.getPos().x);
                pos.putDouble("y",user.getPos().y);
                pos.putDouble("z",user.getPos().z);
                token.getOrCreateNbt().putString("dimension", String.valueOf(user.getWorld().getRegistryKey().getValue()));
                token.getOrCreateNbt().putInt("CustomModelData",1);
            } else {
                assert Objects.requireNonNull(token.getNbt()).getString("dimension") != null;
                if(Objects.equals(String.valueOf(user.getWorld().getRegistryKey().getValue()), token.getNbt().getString("dimension"))) {
                    if (world.isClient) {
                        user.sendMessage(Text.of("[ Position Loaded ]"));
                    }
                    user.fallDistance = 0f;
                    NbtCompound pos = token.getOrCreateNbt().getCompound("pos");
                    user.setPosition(pos.getDouble("x"), pos.getDouble("y"), pos.getDouble("z"));
                    token.getOrCreateNbt().putBoolean("positionSaved", false);
                    token.getOrCreateNbt().putInt("CustomModelData", 0);
                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    if (!user.getAbilities().creativeMode) {
                        user.getStackInHand(hand).decrement(1);
                    }
                }else{
                    if (world.isClient) {
                        user.sendMessage(Text.of("[ Position saved is in a different dimension ]"));
                    }
                }
        }
        user.getItemCooldownManager().set(this, 20);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
