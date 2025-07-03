package net.generic404_.keranite.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.generic404_.keranite.Keranite;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CustomEntities {
    public static final EntityType<JavelinEntity> JAVELIN_ENTITY_TYPE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Keranite.MOD_ID, "javelin_entity"), FabricEntityTypeBuilder.<JavelinEntity>create(SpawnGroup.MISC, JavelinEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f,0.25f)).build());

    public static void initialize() {}
}
