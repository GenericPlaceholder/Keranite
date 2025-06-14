package net.generic404_.keranite;

import net.fabricmc.api.ModInitializer;
import net.generic404_.keranite.block.ModBlocks;
import net.generic404_.keranite.effect.ModEffects;
import net.generic404_.keranite.enchantment.ModEnchantments;
import net.generic404_.keranite.item.ModItemGroups;
import net.generic404_.keranite.item.ModItems;
import net.generic404_.keranite.particle.ParticleInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Keranite implements ModInitializer {
	public static final String MOD_ID = "keranite";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

//		LOGGER.info("Hello Keranite world!");

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerModEffects();
		ModEnchantments.registerModEnchants();
		ParticleInitializer.initialize();
	}
}