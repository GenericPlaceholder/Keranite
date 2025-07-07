package net.generic404_.keranite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.generic404_.keranite.block.ModBlocks;
import net.generic404_.keranite.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.KERANITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.KERANITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_KERANITE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.KERANITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_KERANITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINOR_KERANITE_ANCHOR, Models.GENERATED);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LIGHT_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LIGHT_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LIGHT_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LIGHT_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HEAVY_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HEAVY_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HEAVY_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HEAVY_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.THIGHHIGHS_PINK));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.THIGHHIGHS_BLACK));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.THIGHHIGHS_BLUE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.THIGHHIGHS_TRANS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.THIGHHIGHS_LESB));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.THIGHHIGHS_GAY));
    }
}
