package com.SpectrumFATM.vortexmanipulators.network;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.network.TPacketHandler;

import java.util.Set;
import java.util.function.Supplier;

public class PacketTeleportHandler {
    private String dim;
    private int x;
    private int y;
    private int z;

    private RegistryKey<World> world;

    public PacketTeleportHandler(PacketBuffer buf) {
        this.dim = buf.readUtf();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public PacketTeleportHandler(String dim, int x, int y, int z) {
        this.dim = dim;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeUtf(this.dim);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();

        VortexM.LOGGER.info("Attempting to teleport " + sender.getDisplayName().getString() + " to " + dim + " " + x + " " + y + " " + z);

        Set<RegistryKey<World>> worlds = sender.getServer().levelKeys();
        BlockPos tpPos = new BlockPos(x, y, z);
        RegistryKey<World> selectedWorld;

        for(RegistryKey<World> world: worlds) {
            if (world.location().toString().equals(dim)) {
                selectedWorld = world;
                TPacketHandler.handleVortexMTeleport(sender, tpPos, selectedWorld, false, true);
                break;
            }
        }
    }
}