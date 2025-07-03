package net.generic404_.keranite.mixin.nameHider;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.generic404_.keranite.effect.ModEffects;
import net.generic404_.keranite.util.RandomUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class NameHider {
    @Unique
    protected final PlayerEntity self = (PlayerEntity)(Object)this;

    @Unique
    public final String[][] possibleNames = {
            {
                "Somebody",
                    "Hidden",
                    "Unknown",
                    "Anonymous",
                    "You?",
                    "Me?",
                    "It's Me",
                    "Unidentified",
                    "Him",
                    "Her",
                    "Undisclosed",
                    "Unnamed",
                    "Player",
                    "%1$s",
                    "%2$s",
                    "???"
            },
            {
                "Something",
                    "Creature",
                    "Cryptid",
                    "Thing",
                    "Entity",
                    "It",
                    "???"
            }
    };
    @Unique
    private static Integer randomNumber = 0;
    @Unique
    private static Integer ticks = 15;

    @ModifyReturnValue(method = "getName", at=@At("RETURN"))
    private Text nameChanger(Text original) {
        StatusEffectInstance plrEffect = self.getStatusEffect(ModEffects.OBFUSCATED);
        if(plrEffect!=null) {
            ticks -= 1;
            if(ticks==0){
                randomNumber = RandomUtil.getRandomInt(0,possibleNames[0].length-1);
                ticks = 20;
            }
            return Text.literal(possibleNames[0][randomNumber]).setStyle(Style.EMPTY.withObfuscated(true));
        } else {
            return original;
        }
    }
}
