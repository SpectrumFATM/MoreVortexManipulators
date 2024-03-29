package com.SpectrumFATM.vortexmanipulators.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.cap.Capabilities;
import net.tardis.mod.config.TConfig;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.items.TItems;
import net.tardis.mod.misc.SpaceTimeCoord;
import net.tardis.mod.world.dimensions.TDimensions;

import java.util.List;
import java.util.function.Supplier;

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
        float fuelDischarge = (float)((double)(Integer) TConfig.SERVER.vmBaseFuelUsage.get() + (Double)TConfig.SERVER.vmFuelUsageMultiplier.get() * 500);

        if (sender.getMainHandItem().getItem() == TItems.VORTEX_MANIP.get()){
            stack = sender.getMainHandItem();
        } else if (sender.getOffhandItem().getItem() == TItems.VORTEX_MANIP.get()){
            stack = ctx.get().getSender().getOffhandItem();
        }

        CompoundNBT nbt = stack.getTag();

        List list = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND);
        SpaceTimeCoord spaceTimeCoord = SpaceTimeCoord.deserialize((CompoundNBT) list.get(index - 1));

        sender.getCapability(Capabilities.PLAYER_DATA).ifPresent((playerCap) -> {
            playerCap.setDestination(new SpaceTimeCoord(spaceTimeCoord.getDim(), spaceTimeCoord.getPos()));
            playerCap.calcDisplacement(sender.blockPosition(), spaceTimeCoord.getPos());
        });

        WorldHelper.teleportEntities(sender, sender.level.getServer().getLevel(TDimensions.VORTEX_DIM), new BlockPos(0, 500D, 0), 0, 90);
    }
}