package net.generic404_.keranite.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.generic404_.keranite.Keranite;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class CustomBlockEntities {
    public static <T extends BlockEntity> BlockEntityType<T> register(String path, FabricBlockEntityTypeBuilder.Factory<T> entityFactory, Block block) {
        Identifier id = Identifier.of(Keranite.MOD_ID, path);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.create(entityFactory, block).build());
    }
    //public static final BlockEntityType<RawKeraniteBlockEntity> RAW_KERANITE_BLOCK_ENTITY_TYPE = register("raw_keranite_be", FabricBlockEntityTypeBuilder.create(RawKeraniteBlockEntity::new, ModBlocks.RAW_KERANITE_BLOCK).build());

    public static void initialize() {}
}
