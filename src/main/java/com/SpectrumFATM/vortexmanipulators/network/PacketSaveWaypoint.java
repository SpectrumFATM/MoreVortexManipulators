package com.SpectrumFATM.vortexmanipulators.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.items.TItems;

import java.util.function.Supplier;

public class PacketSaveWaypoint {
    private CompoundNBT waypoint;

    public PacketSaveWaypoint(PacketBuffer buf) {
        this.waypoint = buf.readNbt();
    }

    public PacketSaveWaypoint(CompoundNBT waypoint) {
        this.waypoint = waypoint;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeNbt(this.waypoint);
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
        if (nbt == null) {
            nbt = new CompoundNBT();
        }

        if (nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND) == null) {
            ListNBT list = new ListNBT();
            list.add(waypoint);
            nbt.put("waypoints", list);
            stack.setTag(nbt);
        } else {
            ListNBT list = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND);
            list.add(waypoint);
            nbt.put("waypoints", list);
            stack.setTag(nbt);
        }
    }
}