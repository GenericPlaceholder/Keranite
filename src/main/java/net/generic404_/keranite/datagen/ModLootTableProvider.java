package net.generic404_.keranite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.generic404_.keranite.block.ModBlocks;
import net.generic404_.keranite.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.KERANITE_BLOCK);
        addDrop(ModBlocks.KERANITE_ORE, ModItems.RAW_KERANITE);
    }
}
