package com.SpectrumFATM.vortexmanipulators.network;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.items.TItems;

import java.util.function.Supplier;

public class PacketDeleteWaypoint {

    private int toDelete;
    private ItemStack stack;

    public PacketDeleteWaypoint(PacketBuffer buf) {
        this.toDelete = buf.readInt();
    }

    public PacketDeleteWaypoint(int toDelete) {
        this.toDelete = toDelete;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(toDelete);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getSender().getMainHandItem().getItem() == TItems.VORTEX_MANIP.get()){
            stack = ctx.get().getSender().getMainHandItem();
        } else if (ctx.get().getSender().getOffhandItem().getItem() == TItems.VORTEX_MANIP.get()){
            stack = ctx.get().getSender().getOffhandItem();
        }

        CompoundNBT nbt = stack.getTag();
        if (nbt == null) {
            nbt = new CompoundNBT();
        }
        ListNBT list = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND);
        list.remove(toDelete);
        nbt.put("waypoints", list);
        stack.setTag(nbt);
    }
}