package com.ironking127.create_combat.items;

import com.ironking127.create_combat.entity.NeonTubeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/*////////////////////////////////////////////////////
		The Neon Tube is a throwable torch designed		_______________________
	to be a more easily deployable alternative to the	| Iron     | Slime    |
	regular torch.										| Ingot    | Ball     |
		It is only obtainable using the shaped recipe	|__________|__________|
	defined in the neon_tube.json under recipes. --->	| Electron | Redstone |
		As an item it can be used by the player to be	| Tube     |          |
	launched as an entity where it will fly through		|_____________________|
	the air as a projectile until it collides with a	| Iron     | Slime    |
	block where it will finally place a block version	| Ingot    | Ball     |
	of itself in the most appropriate orientation		|__________|__________|
	possible, or drop the original item if it could
	not place. The block also drops the original item
	when punched like a torch.
		The item is defined here and registered in
	ModItems.
		The projectile entity is defined in entity/
	NeonTubeEntity, is registered in ModEntities, and
	rendered by Flying3dItemEntityRenderer.
		The block is defined in blocks/NeonTubeBlock
	and blockstates/neon_tube_block.json and
	registered in ModBlocks
*/////////////////////////////////////////////////////
public class NeonTube extends Item{

	//Contructor
	public NeonTube(Settings settings) {
		super(settings);
	}

	//Use function
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		//Get the current item stack
		ItemStack itemStack = user.getStackInHand(hand);
		if (!world.isClient) {
			//Create an neon tube entity, give it a velocity, and spawn in into the world
			NeonTubeEntity tubeEntity = new NeonTubeEntity(world, user);
			tubeEntity.setItem(itemStack);

			tubeEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
			world.spawnEntity(tubeEntity);
		}
		//Decrease item amount
		if (!user.getAbilities().creativeMode) {
			itemStack.decrement(1);
		}

		return TypedActionResult.success(itemStack, world.isClient());
	}
}
