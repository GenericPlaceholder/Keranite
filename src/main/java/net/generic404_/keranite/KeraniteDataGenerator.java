package net.generic404_.keranite;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.generic404_.keranite.datagen.ModBlockTagProvider;
import net.generic404_.keranite.datagen.ModItemTagProvider;
import net.generic404_.keranite.datagen.ModModelProvider;
import net.generic404_.keranite.datagen.ModRecipeProvider;

public class KeraniteDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
