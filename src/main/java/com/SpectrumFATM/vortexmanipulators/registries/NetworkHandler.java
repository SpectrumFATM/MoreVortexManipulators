package com.SpectrumFATM.vortexmanipulators.registries;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.network.PacketBioScan;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
    public static SimpleChannel INSTANCE;
    private static int ID = 0;
    private static final String PROTOCOL_VERSION = "1";

    public static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(VortexM.MODID, "main"), () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals);

        INSTANCE.registerMessage(nextID(),
                PacketBioScan.class,
                PacketBioScan::toBytes,
                PacketBioScan::new,
                PacketBioScan::handle);
    }
}
