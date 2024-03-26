package com.ironking127.create_combat.entity;

import com.ironking127.create_combat.CreateCombat;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
	//Entities do a bunch of weird things with types which means they need to be registered individually
	public static final EntityType<NeonTubeEntity> NeonTubeEntityType = Registry.register(
	Registries.ENTITY_TYPE,
			new Identifier(CreateCombat.MODID, "neon_tube_entity"),
			FabricEntityTypeBuilder.<NeonTubeEntity>create(SpawnGroup.MISC, NeonTubeEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
					.trackRangeBlocks(64).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
					.build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
	);

	public static void registerModEntities() {
		CreateCombat.LOGGER.info("Registering Mod Entites for " + CreateCombat.MODID);
	}
}
