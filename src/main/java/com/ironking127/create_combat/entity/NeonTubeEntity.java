package com.ironking127.create_combat.entity;

import com.ironking127.create_combat.blocks.ModBlocks;
import com.ironking127.create_combat.items.ModItems;
import com.ironking127.create_combat.items.NeonTube;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class NeonTubeEntity extends ThrownItemEntity {
	//Has the entity placed a block
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
			if(tryToPlace(hit.getBlockPos().offset(hit.getSide()), hit.getSide().getOpposite())) return;
			dropStack(new ItemStack(ModItems.NEON_TUBE), 0.5f);
		}
	}

	protected boolean tryToPlace(BlockPos pos, Direction dir)
	{
		BlockState blockInDir = this.getWorld().getBlockState(pos.offset(dir));
		if(blockInDir.isSideSolidFullSquare(this.getWorld(), pos.offset(dir), dir.getOpposite()))
		{
			//Place the block
			this.getWorld().setBlockState(pos, ModBlocks.NeonTubeBlock.getDefaultState().with(Properties.FACING, dir.getOpposite()).with(Properties.WATERLOGGED, this.isSubmergedInWater()));
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
}
