package com.SpectrumFATM.vortexmanipulators.vm;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tardis.mod.helper.PlayerHelper;
import net.tardis.mod.helper.TextHelper;
import net.tardis.mod.misc.SpaceTimeCoord;
import net.tardis.mod.network.TPacketHandler;
import net.tardis.mod.vm.BaseVortexMFunction;
import org.lwjgl.system.CallbackI;

import java.util.Random;

public class VortexMRandom extends BaseVortexMFunction {
    Random random = new Random();

    public VortexMRandom() {

    }

    @Override
    public void sendActionOnServer(World world, ServerPlayerEntity player) {
        super.sendActionOnClient(world, player);
        int x = random.nextInt(100);
        int y = random.nextInt(100);

        TPacketHandler.handleVortexMTeleport(player, new BlockPos(x, 64, y), false);
        PlayerHelper.closeVMModel(player);
    }
}
