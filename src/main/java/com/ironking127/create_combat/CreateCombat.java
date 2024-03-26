package com.ironking127.create_combat;

import com.ironking127.create_combat.blocks.ModBlocks;
import com.ironking127.create_combat.entity.ModEntities;
import com.ironking127.create_combat.items.ModItemGroups;
import com.ironking127.create_combat.items.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCombat implements ModInitializer {

	public static final String MODID = "create_combat";
	public static final String NAME = "Create Combat";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEntities.registerModEntities();
		ModBlocks.registerModBlocks();
	}
}