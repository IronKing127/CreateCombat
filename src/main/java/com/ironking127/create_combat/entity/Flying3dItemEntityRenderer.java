package com.ironking127.create_combat.entity;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;

public class Flying3dItemEntityRenderer<T extends Entity & FlyingItemEntity> extends EntityRenderer<T> {

	private static final float MIN_DISTANCE = 12.25F;
	private final ItemRenderer itemRenderer;
	private final float scale;
	private final boolean lit;

	public Flying3dItemEntityRenderer(EntityRendererFactory.Context ctx, float scale, boolean lit) {
		super(ctx);
		this.itemRenderer = ctx.getItemRenderer();
		this.scale = scale;
		this.lit = lit;
	}

	public Flying3dItemEntityRenderer(EntityRendererFactory.Context context) {
		this(context, 1.0F, false);
	}

	protected int getBlockLight(T entity, BlockPos pos) {
		return this.lit ? 15 : super.getBlockLight(entity, pos);
	}

	public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		if (entity.age >= 2 || !(this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(entity) < MIN_DISTANCE)) {
			matrices.push();
			matrices.scale(this.scale, this.scale, this.scale);
			matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getYaw()));
			matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90 - entity.getPitch()));
			this.itemRenderer.renderItem(entity.getStack(), ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), entity.getId());
			matrices.pop();
			super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
		}
	}

	public Identifier getTexture(Entity entity) {
		return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
	}
}
