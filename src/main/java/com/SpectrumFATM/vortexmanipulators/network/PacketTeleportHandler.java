package com.SpectrumFATM.vortexmanipulators.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.items.TItems;
import net.tardis.mod.misc.SpaceTimeCoord;
import net.tardis.mod.network.TPacketHandler;

import java.util.List;
import java.util.function.Supplier;


//Change this to take the gui index value and read player item NBT.
public class PacketTeleportHandler {

    private int index;

    public PacketTeleportHandler(PacketBuffer buf) {
        this.index = buf.readInt();
    }

    public PacketTeleportHandler(int index) {
        this.index = index;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(this.index);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        ItemStack stack = null;

        if (sender.getMainHandItem().getItem() == TItems.VORTEX_MANIP.get()){
            stack = sender.getMainHandItem();
        } else if (sender.getOffhandItem().getItem() == TItems.VORTEX_MANIP.get()){
            stack = ctx.get().getSender().getOffhandItem();
        }

        CompoundNBT nbt = stack.getTag();

        List list = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND);
        SpaceTimeCoord spaceTimeCoord = SpaceTimeCoord.deserialize((CompoundNBT) list.get(index - 1));

        TPacketHandler.handleVortexMTeleport(sender, spaceTimeCoord.getPos(), spaceTimeCoord.getDim(), true, true);
    }
}