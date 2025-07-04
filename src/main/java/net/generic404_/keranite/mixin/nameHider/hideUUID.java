package net.generic404_.keranite.mixin.nameHider;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.generic404_.keranite.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.UUID;

@Mixin(Entity.class)
public abstract class hideUUID {
    @Unique
    protected final Entity self = (Entity)(Object)this;

    @ModifyReturnValue(method = "getHoverEvent",at=@At("RETURN"))
    private HoverEvent obfuscateUUID(HoverEvent original) {
        if (self instanceof LivingEntity livingEntity&&livingEntity.hasStatusEffect(ModEffects.OBFUSCATED)){
            return new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new HoverEvent.EntityContent(EntityType.MARKER, UUID.randomUUID(), Text.literal("Horse").setStyle(Style.EMPTY.withObfuscated(true))));
        } else { return original; }
    }

    @ModifyReturnValue(method = "getDisplayName",at=@At("RETURN"))
    private Text obfuscateDisplayUUID(Text original){
        if (self instanceof LivingEntity livingEntity&&livingEntity.hasStatusEffect(ModEffects.OBFUSCATED)){
            return Team.decorateName(self.getScoreboardTeam(), self.getName())
                    .styled(style -> style.withHoverEvent(
                            new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new HoverEvent.EntityContent(EntityType.PLAYER, UUID.randomUUID(), Text.literal("Horse").setStyle(Style.EMPTY.withObfuscated(true))))
                    ).withInsertion(UUID.randomUUID().toString()));
        } else { return original; }
    }
}
