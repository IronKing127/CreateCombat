package com.ironking127.create_combat.items;

import com.ironking127.create_combat.CreateCombat;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {
	public static final Item modMenuIcon = registerItem("rose_katana", new Item(new FabricItemSettings()));

	public static final Item NEON_TUBE = registerItem("neon_tube",  new NeonTube(new FabricItemSettings()));

	private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
		entries.add(NEON_TUBE);
	}
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(CreateCombat.MODID, name), item);
	}

	public static void registerModItems() {
		CreateCombat.LOGGER.info("Registering Mod Items for " + CreateCombat.MODID);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
	}
}
