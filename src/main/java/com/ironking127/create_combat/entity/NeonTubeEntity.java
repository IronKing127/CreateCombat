package com.ironking127.create_combat.entity;

import com.ironking127.create_combat.blocks.ModBlocks;
import com.ironking127.create_combat.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class NeonTubeEntity extends ThrownItemEntity {
	//Has the entity placed a block
	boolean success = false;
	//Contructors
	public NeonTubeEntity(EntityType<? extends NeonTubeEntity> entityType, World world) {
		super(entityType, world);
	}

	public NeonTubeEntity(World world, LivingEntity owner) {
		super(ModEntities.NeonTubeEntityType, owner, world);
	}

	public NeonTubeEntity(World world, double x, double y, double z){
		super(ModEntities.NeonTubeEntityType, x, y, z, world);
	}
	//Collision
	//The super handles all collisions calling onBlockHit before the rest of the code if it is a block collision
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (!this.getWorld().isClient) {
			//if(!success) //Drop loot
			this.kill();
		}
	}
	//Called on block hit
	protected void onBlockHit(BlockHitResult hit)
	{
		if (!this.getWorld().isClient) {
			//Algorithm to place the block
			/*
				Works by ranking the dimensions according to magnetude and prioritizes placing the block
				in the direction the projectile was most going with the opposites of each dimension having
				the least priority.
			 */
			double magx = Math.abs(this.getVelocity().getX());
			double magy = Math.abs(this.getVelocity().getY());
			double magz = Math.abs(this.getVelocity().getZ());
			if(magx > magy && magx > magz)
			{
				//X is the greatest dimension
				if(magy > magz)
				{
					//Y is 2nd greatest dimension
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.EAST : Direction.WEST)) return;
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.UP : Direction.DOWN)) return;
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.SOUTH : Direction.NORTH)) return;
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.NORTH : Direction.SOUTH)) return;
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.DOWN : Direction.UP)) return;
					tryToPlace((this.getVelocity().x > 0) ? Direction.WEST : Direction.EAST);

				}
				else
				{
					//Z is 2nd greatest dimension
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.EAST : Direction.WEST)) return;
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.SOUTH : Direction.NORTH)) return;
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.UP : Direction.DOWN)) return;
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.DOWN : Direction.UP)) return;
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.NORTH : Direction.SOUTH)) return;
					tryToPlace((this.getVelocity().x > 0) ? Direction.WEST : Direction.EAST);
				}
			}
			else if(magy > magx && magy > magz)
			{
				//Y is the greatest dimension
				if(magx > magz)
				{
					//X is 2nd greatest dimension
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.UP : Direction.DOWN)) return;
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.EAST : Direction.WEST)) return;
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.SOUTH : Direction.NORTH)) return;
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.NORTH : Direction.SOUTH)) return;
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.WEST : Direction.EAST)) return;
					tryToPlace((this.getVelocity().y > 0) ? Direction.DOWN : Direction.UP);

				}
				else
				{
					//Z is 2nd greatest dimension
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.UP : Direction.DOWN)) return;
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.SOUTH : Direction.NORTH)) return;
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.EAST : Direction.WEST)) return;
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.WEST : Direction.EAST)) return;
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.NORTH : Direction.SOUTH)) return;
					tryToPlace((this.getVelocity().y > 0) ? Direction.DOWN : Direction.UP);
				}
			}
			else
			{
				//Z is the greatest dimension
				if(magx > magy)
				{
					//X is 2nd greatest dimension
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.SOUTH : Direction.NORTH)) return;
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.EAST : Direction.WEST)) return;
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.UP : Direction.DOWN)) return;
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.DOWN : Direction.UP)) return;
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.WEST : Direction.EAST)) return;
					tryToPlace((this.getVelocity().z > 0) ? Direction.NORTH : Direction.SOUTH);
				}
				else
				{
					//Y is 2nd greatest dimension
					if(tryToPlace((this.getVelocity().z > 0) ? Direction.SOUTH : Direction.NORTH)) return;
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.UP : Direction.DOWN)) return;
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.EAST : Direction.WEST)) return;
					if(tryToPlace((this.getVelocity().x > 0) ? Direction.WEST : Direction.EAST)) return;
					if(tryToPlace((this.getVelocity().y > 0) ? Direction.DOWN : Direction.UP)) return;
					tryToPlace((this.getVelocity().z > 0) ? Direction.NORTH : Direction.SOUTH);
				}
			}
		}
	}

	protected boolean tryToPlace(Direction dir)
	{
		BlockState blockInDir = this.getWorld().getBlockState(this.getBlockPos().offset(dir));
		if(blockInDir.isSideSolidFullSquare(this.getWorld(), this.getBlockPos().offset(dir), dir.getOpposite()))
		{
			//Place the block
			this.getWorld().setBlockState(this.getBlockPos(), ModBlocks.NeonTubeBlock.getDefaultState().with(Properties.FACING, dir.getOpposite()));
			//Write NBT data
			//The block has been placed!
			return true;
		}
		return false;
	}

	//Returns item this entity represents
	@Override
	protected Item getDefaultItem() {
		return ModItems.NEON_TUBE;
	}
	static{

	}
}
