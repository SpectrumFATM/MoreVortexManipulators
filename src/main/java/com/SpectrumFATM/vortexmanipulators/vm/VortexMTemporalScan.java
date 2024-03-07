package com.SpectrumFATM.vortexmanipulators.vm;

import com.SpectrumFATM.vortexmanipulators.network.PacketTemporalScan;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.tardis.mod.helper.PlayerHelper;
import net.tardis.mod.vm.BaseVortexMFunction;

public class VortexMTemporalScan extends BaseVortexMFunction {

    public VortexMTemporalScan() {

    }

    @Override
    public void sendActionOnServer(World world, ServerPlayerEntity player) {
        super.sendActionOnClient(world, player);
        NetworkHandler.INSTANCE.sendToServer(new PacketTemporalScan());
        PlayerHelper.closeVMModel(player);
        Minecraft.getInstance().setScreen(null);
    }
}
