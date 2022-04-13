package com.SpectrumFATM.vortexmanipulators.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.helper.TextHelper;

import java.util.function.Supplier;

public class PacketLocator {
    private String structure;

    public PacketLocator(PacketBuffer buf) {
        this.structure = buf.readUtf();

    }

    public PacketLocator(String structure) {
        this.structure = structure;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeUtf(this.structure);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        Structure<?> selectedStructure = ForgeRegistries.STRUCTURE_FEATURES.getValue(new ResourceLocation(structure));

        if (selectedStructure == null) {
            sender.displayClientMessage(TextHelper.createVortexManipMessage(new StringTextComponent("Error! Unable to locate this structure.")), false);
        }

        BlockPos location = sender.getLevel().findNearestMapFeature(selectedStructure, new BlockPos(sender.getX(), sender.getY(), sender.getZ()), 100, false);

        if (location != null) {
            sender.displayClientMessage(TextHelper.createVortexManipMessage(new StringTextComponent("Located at the following coordinates: " + location.getX() + " ~ " + location.getZ())), false);
        } else {
            sender.displayClientMessage(TextHelper.createVortexManipMessage(new StringTextComponent("Error! Unable to locate this structure.")), false);
        }
    }
}