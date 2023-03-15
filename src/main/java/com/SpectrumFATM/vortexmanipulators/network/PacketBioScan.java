package com.SpectrumFATM.vortexmanipulators.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketBioScan {
    private String player;

    public PacketBioScan(PacketBuffer buf) {
        this.player = buf.readUtf();

    }

    public PacketBioScan(String player) {
        this.player = player;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeUtf(this.player);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        ServerPlayerEntity selected = sender.getServer().getPlayerList().getPlayerByName(player);

        if (sender.getX() - 16 < selected.getX() && sender.getX() + 16 > selected.getX() & sender.getY() - 16 < selected.getY() && sender.getY() + 16 > selected.getY() & sender.getZ() - 16 < selected.getZ() && sender.getZ() + 16 > selected.getZ()) {
            sender.displayClientMessage(new TranslationTextComponent("Showing medical information for " + selected.getDisplayName().getString() + ":"), false);
            sender.displayClientMessage(new TranslationTextComponent("Health: " + selected.getHealth() + "/" + selected.getMaxHealth()), false);
            sender.displayClientMessage(new TranslationTextComponent("Hunger: " + selected.getFoodData().getFoodLevel() + "/20"), false);
            sender.displayClientMessage(new TranslationTextComponent("Coords: " + (int) selected.getX() + " " + (int) selected.getY() + " " + (int) selected.getZ()), false);
            sender.displayClientMessage(new TranslationTextComponent("Dimension: " + selected.level.dimension().location()), false);
        }
    }
}