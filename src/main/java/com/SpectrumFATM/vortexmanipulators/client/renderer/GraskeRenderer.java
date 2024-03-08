package com.SpectrumFATM.vortexmanipulators.client.renderer;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.client.model.GraskeModel;
import com.SpectrumFATM.vortexmanipulators.entities.GraskeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GraskeRenderer extends MobRenderer<GraskeEntity, GraskeModel<GraskeEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(VortexM.MODID, "textures/entity/graske.png");

    public GraskeRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new GraskeModel<>(), 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(GraskeEntity p_110775_1_) {
        return TEXTURE;
    }
}
