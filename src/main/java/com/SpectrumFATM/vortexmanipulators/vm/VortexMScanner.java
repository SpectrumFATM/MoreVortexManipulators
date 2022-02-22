package com.SpectrumFATM.vortexmanipulators.vm;

import com.SpectrumFATM.vortexmanipulators.client.gui.vm.VMScanner;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.tardis.mod.vm.BaseVortexMFunction;

public class VortexMScanner extends BaseVortexMFunction {

    public VortexMScanner() {

    }

    @Override
    public void sendActionOnClient(World world, PlayerEntity player) {
        super.sendActionOnClient(world, player);
        Minecraft.getInstance().setScreen(new VMScanner());
    }
}
