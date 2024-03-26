package com.ironking127.create_combat;

import com.ironking127.create_combat.entity.Flying3dItemEntityRenderer;
import com.ironking127.create_combat.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CreateCombatClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntities.NeonTubeEntityType, (context) ->
				new Flying3dItemEntityRenderer(context, 1, true));
	}
}
