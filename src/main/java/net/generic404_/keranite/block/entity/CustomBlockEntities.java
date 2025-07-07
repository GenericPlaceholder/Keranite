package net.generic404_.keranite.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class CustomBlockEntities {
    public static final BlockEntityType<RawKeraniteBlockEntity> RAW_KERANITE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Keranite.MOD_ID, "raw_keranite_be"),
                    FabricBlockEntityTypeBuilder.create(RawKeraniteBlockEntity::new,
                            ModBlocks.RAW_KERANITE_BLOCK).build());

    public static void initialize() {}
}
