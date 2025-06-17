package net.generic404_.keranite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.generic404_.keranite.block.ModBlocks;
import net.generic404_.keranite.effect.ModEffects;
import net.generic404_.keranite.enchantment.ModEnchantments;
import net.generic404_.keranite.item.ModItemGroups;
import net.generic404_.keranite.item.ModItems;
import net.generic404_.keranite.particle.ParticleInitializer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Keranite implements ModInitializer {
	public static final String MOD_ID = "keranite";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RegistryKey<PlacedFeature> KERANITE_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID,"keranite_ore"));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, KERANITE_ORE_PLACED_KEY);

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerModEffects();
		ModEnchantments.registerModEnchants();
		ParticleInitializer.initialize();
	}
}