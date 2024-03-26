package com.ironking127.create_combat.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RodBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;

public class NeonTubeBlock extends RodBlock {

	public NeonTubeBlock(Settings settings) {
		super(settings);
	}

	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getSide();
		BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.getOpposite()));
		return blockState.isOf(this) && blockState.get(FACING) == direction ? (BlockState)this.getDefaultState().with(FACING, direction.getOpposite()) : (BlockState)this.getDefaultState().with(FACING, direction);
	}


	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(Properties.FACING);
	}
}
