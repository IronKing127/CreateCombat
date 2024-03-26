package com.ironking127.create_combat.blocks;

import com.ironking127.create_combat.CreateCombat;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
	public static final Block NeonTubeBlock = registerBlock("neon_tube_block",
			new Block(FabricBlockSettings.copyOf(Blocks.END_ROD)));
	private static Block registerBlock(String name, Block block) {
		return Registry.register(Registries.BLOCK, new Identifier(CreateCombat.MODID, name), block);
	}
	public static void registerModBlocks() {
		CreateCombat.LOGGER.info("Registering Mod Blocks for " + CreateCombat.MODID);
	}
}
