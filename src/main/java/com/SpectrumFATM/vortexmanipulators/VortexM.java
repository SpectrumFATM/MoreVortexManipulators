package com.SpectrumFATM.vortexmanipulators;

import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import com.SpectrumFATM.vortexmanipulators.registries.VortexFunctions;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


//TO DO
//Rename all relevant classes.

// The value here should match an entry in the META-INF/mods.toml file
@Mod("vortexmanipulators")
public class VortexM {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "vortexmanipulators";

    public VortexM() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        VortexFunctions.FUNCTIONS.register(modBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        VortexFunctions.addFunctionToCategories();
        NetworkHandler.registerMessages();
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
    }
}