package com.SpectrumFATM.vortexmanipulators.client.model;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.SpectrumFATM.vortexmanipulators.entities.GraskeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GraskeModel<G extends GraskeEntity> extends EntityModel<G> {
	private final ModelRenderer head;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;

	public GraskeModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 6.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.texOffs(36, 39).addBox(-0.8F, -12.0426F, 0.2426F, 1.6F, 1.7F, 4.0F, 0.0F, false);
		head.texOffs(42, 28).addBox(6.4426F, -7.8F, 0.2426F, 1.6F, 1.6F, 4.0F, 0.0F, false);
		head.texOffs(0, 44).addBox(-8.0426F, -7.8F, 0.2426F, 1.6F, 1.6F, 4.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-5.4142F, -7.0F, -1.1716F);
		head.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
		cube_r1.texOffs(40, 13).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, 7.0215F, -6.4888F);
		head.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.3927F, 0.0F, 0.0F);
		cube_r2.texOffs(36, 13).addBox(-7.8426F, -17.6F, 4.2426F, 1.1F, 1.2F, 4.5F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(6.2565F, -7.0F, 2.2957F);
		head.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.7854F, 0.0F);
		cube_r3.texOffs(32, 31).addBox(0.8562F, -1.0F, -6.0473F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(7.2426F, -9.357F, -1.6068F);
		head.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.3927F, 0.0F, 0.0F);
		cube_r4.texOffs(12, 44).addBox(-0.6F, -0.6F, 6.0F, 1.1F, 1.2F, 4.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.0F, -9.3203F, 5.7629F);
		head.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.7854F, 0.0F, 0.0F);
		cube_r5.texOffs(40, 21).addBox(-0.5F, -0.55F, -3.0F, 1.0F, 1.1F, 6.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(-5.0F, -8.0F, -1.1716F);
		head.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.7854F, 0.0F, 0.0F);
		cube_r6.texOffs(26, 39).addBox(4.0F, -2.0F, -2.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(8.0F, 0.0F, -8.0F);
		body.texOffs(0, 16).addBox(-12.0F, 6.0F, 6.0F, 8.0F, 9.0F, 4.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(-4.0F, 7.833F, 0.0F);
		leftArm.texOffs(0, 29).addBox(-4.0F, -1.833F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(4.0F, 7.833F, 0.0F);
		rightArm.texOffs(24, 16).addBox(0.0F, -1.833F, -2.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(-1.9F, 15.0F, 0.0F);
		leftLeg.texOffs(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(1.9F, 15.0F, 0.0F);
		rightLeg.texOffs(16, 31).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(G entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		// Adjust the legs
		this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		// Adjust the arms
		float armSwing = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		float armSwingOpposite = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		// Limit the range of arm movement
		float maxArmSwing = 0.5F; // Adjust as needed
		this.leftArm.xRot = MathHelper.clamp(armSwing, -maxArmSwing, maxArmSwing);
		this.rightArm.xRot = MathHelper.clamp(armSwingOpposite, -maxArmSwing, maxArmSwing);
	}



	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}