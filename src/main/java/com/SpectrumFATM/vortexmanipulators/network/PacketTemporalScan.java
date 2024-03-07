package com.SpectrumFATM.vortexmanipulators.network;

//Write a new base packet that doesn't take any parameters

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.cap.Capabilities;

import java.util.function.Supplier;

public class PacketTemporalScan {

    private BlockPos pos;

    public PacketTemporalScan(PacketBuffer buf) {
    }

    public PacketTemporalScan() {
    }

    public void toBytes(PacketBuffer buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();

        Chunk c = sender.getLevel().getChunkAt(sender.blockPosition());

        //If the chunk has the rift capability
        c.getCapability(Capabilities.RIFT).ifPresent((rift) -> {
            //If the rift is active
            if (rift.isRift()) {
                sender.displayClientMessage(new StringTextComponent("Temporal Scan: Rift Detected with " + rift.getRiftEnergy() + " AU").withStyle(TextFormatting.BOLD).withStyle(TextFormatting.GRAY), false);
            } else {
                sender.displayClientMessage(new StringTextComponent("Temporal Scan: No results!").withStyle(TextFormatting.BOLD).withStyle(TextFormatting.GRAY), false);
            }
        });
    }
}
