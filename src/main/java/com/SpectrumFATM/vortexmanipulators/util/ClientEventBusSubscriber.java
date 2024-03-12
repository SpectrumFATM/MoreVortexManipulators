package com.SpectrumFATM.vortexmanipulators.util;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.client.renderer.GraskeRenderer;
import com.SpectrumFATM.vortexmanipulators.client.renderer.TimeFissureRenderer;
import com.SpectrumFATM.vortexmanipulators.entities.GraskeEntity;
import com.SpectrumFATM.vortexmanipulators.entities.TimeFissureEntity;
import com.SpectrumFATM.vortexmanipulators.registries.MobRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = VortexM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(MobRegistry.FISSURE.get(), TimeFissureRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(MobRegistry.GRASKE.get(), GraskeRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterAttributes(EntityAttributeCreationEvent event) {
        event.put(MobRegistry.FISSURE.get(), TimeFissureEntity.setCustomAttribute().build());
        event.put(MobRegistry.GRASKE.get(), GraskeEntity.setCustomAttribute().build());
    }
}
