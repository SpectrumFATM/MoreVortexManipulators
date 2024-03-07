package com.SpectrumFATM.vortexmanipulators.registries;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.network.*;
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

        INSTANCE.registerMessage(nextID(),
                PacketSaveWaypoint.class,
                PacketSaveWaypoint::toBytes,
                PacketSaveWaypoint::new,
                PacketSaveWaypoint::handle);

        INSTANCE.registerMessage(nextID(),
                PacketTeleportHandler.class,
                PacketTeleportHandler::toBytes,
                PacketTeleportHandler::new,
                PacketTeleportHandler::handle);

        INSTANCE.registerMessage(nextID(),
                PacketDeleteWaypoint.class,
                PacketDeleteWaypoint::toBytes,
                PacketDeleteWaypoint::new,
                PacketDeleteWaypoint::handle);
        
        INSTANCE.registerMessage(nextID(),
                PacketLocator.class,
                PacketLocator::toBytes,
                PacketLocator::new,
                PacketLocator::handle);

        INSTANCE.registerMessage(nextID(),
                PacketTemporalDisplacement.class,
                PacketTemporalDisplacement::toBytes,
                PacketTemporalDisplacement::new,
                PacketTemporalDisplacement::handle);

        INSTANCE.registerMessage(nextID(),
                PacketTemporalShift.class,
                PacketTemporalShift::toBytes,
                PacketTemporalShift::new,
                PacketTemporalShift::handle);

        INSTANCE.registerMessage(nextID(),
                PacketShareWaypoint.class,
                PacketShareWaypoint::toBytes,
                PacketShareWaypoint::new,
                PacketShareWaypoint::handle);

        INSTANCE.registerMessage(nextID(),
                PacketTimeFissure.class,
                PacketTimeFissure::toBytes,
                PacketTimeFissure::new,
                PacketTimeFissure::handle);

        INSTANCE.registerMessage(nextID(),
                PacketRiftManipulator.class,
                PacketRiftManipulator::toBytes,
                PacketRiftManipulator::new,
                PacketRiftManipulator::handle);

        INSTANCE.registerMessage(nextID(),
                PacketTemporalScan.class,
                PacketTemporalScan::toBytes,
                PacketTemporalScan::new,
                PacketTemporalScan::handle);
    }
}
 