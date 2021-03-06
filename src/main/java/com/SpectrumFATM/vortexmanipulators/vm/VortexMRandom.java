package com.SpectrumFATM.vortexmanipulators.vm;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.helper.PlayerHelper;
import net.tardis.mod.network.TPacketHandler;
import net.tardis.mod.vm.BaseVortexMFunction;

import java.util.Random;

public class VortexMRandom extends BaseVortexMFunction {
    Random random = new Random();

    public VortexMRandom() {

    }

    @Override
    public void sendActionOnServer(World world, ServerPlayerEntity player) {
        super.sendActionOnClient(world, player);
        int positive = random.nextInt(2);
        double x;
        double z;

        if (positive == 1) {
           x = player.getX() + random.nextInt(100);
           z = player.getZ() + random.nextInt(100);
        } else {
            x = player.getX() - random.nextInt(100);
            z = player.getZ() - random.nextInt(100);
        }

        TPacketHandler.handleVortexMTeleport(player, new BlockPos(x, 64, z), false);
        PlayerHelper.closeVMModel(player);
    }
}
