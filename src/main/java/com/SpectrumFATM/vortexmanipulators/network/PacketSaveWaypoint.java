package com.SpectrumFATM.vortexmanipulators.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.items.TItems;

import java.util.function.Supplier;

public class PacketSaveWaypoint {
    private String dim;
    private ItemStack stack;
    private BlockPos pos;
    private String name;

    public PacketSaveWaypoint(PacketBuffer buf) {
        this.dim = buf.readUtf();
        this.name = buf.readUtf();
    }

    public PacketSaveWaypoint(String dim, String name) {
        this.dim = dim;
        this.name = name;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeUtf(this.dim);
        buf.writeUtf(this.name);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        pos = new BlockPos(sender.getX(), sender.getY(), sender.getZ());

        if (name == null) {
            name = "Unnamed";
        }

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
            CompoundNBT waypoint = new CompoundNBT();
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            waypoint.putString("name", name);
            waypoint.putInt("x", x);
            waypoint.putInt("y", y);
            waypoint.putInt("z", z);
            waypoint.putString("dim", dim);
            list.add(waypoint);
            nbt.put("waypoints", list);
            stack.setTag(nbt);
        } else {
            ListNBT list = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND);
            CompoundNBT waypoint = new CompoundNBT();
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            waypoint.putString("name", name);
            waypoint.putInt("x", x);
            waypoint.putInt("y", y);
            waypoint.putInt("z", z);
            waypoint.putString("dim", dim);
            list.add(waypoint);
            nbt.put("waypoints", list);
            stack.setTag(nbt);
        }
    }
}