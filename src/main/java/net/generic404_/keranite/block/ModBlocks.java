package net.generic404_.keranite.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.generic404_.keranite.Keranite;
import net.generic404_.keranite.item.block.RawKeraniteBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block KERANITE_BLOCK = registerBlock("keranite_block", new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).hardness(75).luminance(6).instrument(Instrument.BIT)));
    public static final Block KERANITE_ORE = registerBlock("keranite_ore", new Block(FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS).luminance(2)));
    public static final Block RAW_KERANITE_BLOCK = registerBlock("raw_keranite_block", new RawKeraniteBlock(FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS).luminance(4)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Keranite.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Keranite.MOD_ID, name),
                new RawKeraniteBlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {

    }
}
