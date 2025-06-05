package net.generic404_.keranite.projectiles;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.generic404_.keranite.Keranite;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CustomProjectile {

    public static final EntityType<ConcussionBomb> ConcussionBombEntityType = Registry.register(Registries.ENTITY_TYPE, new Identifier(Keranite.MOD_ID, "concussion_bomb"),
            FabricEntityTypeBuilder.<ConcussionBomb>create(SpawnGroup.MISC, ConcussionBomb::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build());

    public static void initialize(){}
}