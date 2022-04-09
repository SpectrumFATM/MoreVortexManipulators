package com.SpectrumFATM.vortexmanipulators.vm;

import com.SpectrumFATM.vortexmanipulators.client.gui.vm.VMLocator;
import com.SpectrumFATM.vortexmanipulators.client.gui.vm.VMScanner;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tardis.mod.client.ClientHelper;
import net.tardis.mod.helper.PlayerHelper;
import net.tardis.mod.vm.BaseVortexMFunction;

public class VortexMLocator extends BaseVortexMFunction {

    public VortexMLocator() {

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void sendActionOnClient(World world, PlayerEntity player) {
        super.sendActionOnClient(world, player);

        ClientHelper.openGui(new VMLocator());
        PlayerHelper.closeVMModel(player);
    }
}
