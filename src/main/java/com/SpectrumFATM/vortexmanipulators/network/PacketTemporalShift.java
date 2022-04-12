package com.SpectrumFATM.vortexmanipulators.network;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.cap.Capabilities;
import net.tardis.mod.helper.LandingSystem;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.misc.SpaceTimeCoord;
import net.tardis.mod.sounds.TSounds;
import net.tardis.mod.world.dimensions.TDimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class PacketTemporalShift {
    private Random random = new Random();

    public PacketTemporalShift(PacketBuffer buf) {
    }

    public PacketTemporalShift() {
    }

    public void toBytes(PacketBuffer buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();

        sender.level.playLocalSound(sender.getX(), sender.getY(), sender.getZ(), TSounds.SONIC_BROKEN.get(), SoundCategory.AMBIENT, 1F, 1F, true);
        sender.setItemInHand(sender.getUsedItemHand(), ItemStack.EMPTY);

        BlockPos randomCoords = generateCoordinates(sender);
        RegistryKey<World> randomWorld = getRandomWorldKey(sender);

        BlockPos destination = LandingSystem.getTopBlock(sender.getServer().getLevel(randomWorld), randomCoords);

        sender.getCapability(Capabilities.PLAYER_DATA).ifPresent((playerCap) -> {
            playerCap.setDestination(new SpaceTimeCoord(randomWorld,destination));
            playerCap.calcDisplacement(sender.blockPosition(), destination);
        });

        WorldHelper.teleportEntities(sender, sender.level.getServer().getLevel(TDimensions.VORTEX_DIM), new BlockPos(0.0D, 3000D, 0.0D), 0.0F, 90.0F);

        int chance = random.nextInt(10);
        if (chance == 0) {
            sender.hurt(DamageSource.GENERIC, 16f);
        }
    }

    public BlockPos generateCoordinates(ServerPlayerEntity sender) {
        double x;
        double z;
        int positiveNegative;

        positiveNegative = random.nextInt(2);

        if (positiveNegative == 1) {
            x = sender.getX() + random.nextInt(10000);
        } else {
            x = sender.getX() - random.nextInt(10000);
        }

        positiveNegative = random.nextInt(2);

        if (positiveNegative == 1) {
            z = sender.getZ() + random.nextInt(10000);
        } else {
            z = sender.getZ() - random.nextInt(10000);
        }

        return new BlockPos(x, 64, z);
    }

    public RegistryKey<World> getRandomWorldKey(ServerPlayerEntity sender) {
        Iterable<ServerWorld> serverWorld = sender.getServer().getAllLevels();
        List<ServerWorld> worlds = new ArrayList<>();

        serverWorld.forEach((world) -> {
            if (WorldHelper.getAllValidDimensions().contains(world)) {
                worlds.add(world);
            }
        });

        int randomIndex = random.nextInt(worlds.size() - 1);

        while (worlds.get(randomIndex).dimension().location().toString().equals("minecraft:the_end") || worlds.get(randomIndex).dimension().location().toString().equals("minecraft:the_nether")) {
            randomIndex = random.nextInt(worlds.size() - 1);
        }

        return WorldHelper.getWorldKeyFromRL(worlds.get(randomIndex).dimension().location());
    }
}