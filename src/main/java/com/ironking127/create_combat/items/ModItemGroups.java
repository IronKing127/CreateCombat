package com.ironking127.create_combat.items;

import com.ironking127.create_combat.CreateCombat;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
public class ModItemGroups {
	public static final ItemGroup Create_Combat_Group = Registry.register(Registries.ITEM_GROUP,
			new Identifier(CreateCombat.MODID, "create_combat"),
			FabricItemGroup.builder().displayName(Text.translatable("itemgroup.create_combat"))
					.icon(() -> new ItemStack(ModItems.modMenuIcon)).entries((displayContext, entries) -> {
						entries.add(ModItems.NEON_TUBE);
					}).build());

	public static void registerItemGroups() {
		CreateCombat.LOGGER.info("Registering Item Groups for " + CreateCombat.MODID);
	}
}
