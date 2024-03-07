package com.SpectrumFATM.vortexmanipulators.network;

import com.SpectrumFATM.vortexmanipulators.util.VortexUtil;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.cap.Capabilities;
import net.tardis.mod.helper.LandingSystem;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.misc.SpaceTimeCoord;
import net.tardis.mod.world.dimensions.TDimensions;

import java.util.function.Supplier;

public class PacketTimeFissure {

    public PacketTimeFissure(PacketBuffer buf) {
    }

    public PacketTimeFissure() {
    }

    public void toBytes(PacketBuffer buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();

        BlockPos coords = VortexUtil.generateCoordinates(sender);
        RegistryKey<World> worldKey = VortexUtil.getRandomWorldKey(sender);

        BlockPos destination = LandingSystem.getTopBlock(sender.getServer().getLevel(worldKey), coords);

        sender.getCapability(Capabilities.PLAYER_DATA).ifPresent((playerCap) -> {
            playerCap.setDestination(new SpaceTimeCoord(worldKey,destination));
            playerCap.calcDisplacement(sender.blockPosition(), destination);
        });

        WorldHelper.teleportEntities(sender, sender.level.getServer().getLevel(TDimensions.VORTEX_DIM), new BlockPos(0.0D, 1000D, 0.0D), 0.0F, 90.0F);

        sender.hurt(DamageSource.GENERIC, 10F);
    }
}