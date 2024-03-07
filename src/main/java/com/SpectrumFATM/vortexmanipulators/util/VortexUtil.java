package com.SpectrumFATM.vortexmanipulators.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tardis.mod.helper.WorldHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VortexUtil {

    public static RegistryKey<World> getRandomWorldKey(ServerPlayerEntity sender) {
        Iterable<ServerWorld> serverWorld = sender.getServer().getAllLevels();
        List<ServerWorld> worlds = new ArrayList<>();
        Random random = new Random();

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


    public static BlockPos generateCoordinates(ServerPlayerEntity sender) {
        double x;
        double z;
        int positiveNegative;
        Random random = new Random();

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
}
