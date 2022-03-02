package com.SpectrumFATM.vortexmanipulators.vm;

import com.SpectrumFATM.vortexmanipulators.client.gui.vm.VMWaypoint;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tardis.mod.client.ClientHelper;
import net.tardis.mod.helper.PlayerHelper;
import net.tardis.mod.vm.BaseVortexMFunction;

public class VortexMWaypoint extends BaseVortexMFunction {

    public VortexMWaypoint() {
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void sendActionOnClient(World world, PlayerEntity player) {
        super.sendActionOnClient(world, player);

        ClientHelper.openGui(new VMWaypoint());
        PlayerHelper.closeVMModel(player);
    }
}
