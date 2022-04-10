package com.SpectrumFATM.vortexmanipulators;

import com.SpectrumFATM.vortexmanipulators.registries.BlockRegistry;
import com.SpectrumFATM.vortexmanipulators.registries.ItemRegistry;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import com.SpectrumFATM.vortexmanipulators.registries.VortexFunctions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("vortexmanipulators")
public class VortexM {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "vortexmanipulators";

    public VortexM() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
        VortexFunctions.FUNCTIONS.register(modBus);
        BlockRegistry.init();
        ItemRegistry.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
        VortexFunctions.addFunctionToCategories();
        NetworkHandler.registerMessages();
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
    }
}