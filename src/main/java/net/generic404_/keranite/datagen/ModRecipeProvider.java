package net.generic404_.keranite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.generic404_.keranite.block.ModBlocks;
import net.generic404_.keranite.item.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> KERANITE_SMELTABLES = List.of(ModItems.RAW_KERANITE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, KERANITE_SMELTABLES, RecipeCategory.MISC, ModItems.KERANITE_INGOT,
                5f, 500, "keranite");
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.KERANITE_INGOT, RecipeCategory.DECORATIONS, ModBlocks.KERANITE_BLOCK);
    }
}
