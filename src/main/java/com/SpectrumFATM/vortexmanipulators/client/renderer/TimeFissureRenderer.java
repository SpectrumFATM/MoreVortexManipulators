package com.SpectrumFATM.vortexmanipulators.client.renderer;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.client.model.TimeFissureModel;
import com.SpectrumFATM.vortexmanipulators.entities.TimeFissureEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TimeFissureRenderer extends MobRenderer<TimeFissureEntity, TimeFissureModel<TimeFissureEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(VortexM.MODID, "textures/entity/time_fissure.png");

    public TimeFissureRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new TimeFissureModel<>(), 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(TimeFissureEntity p_110775_1_) {
        return TEXTURE;
    }
}
