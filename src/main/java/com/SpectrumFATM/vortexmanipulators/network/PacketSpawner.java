package com.SpectrumFATM.vortexmanipulators.network;

import com.SpectrumFATM.vortexmanipulators.entities.GraskeEntity;
import com.SpectrumFATM.vortexmanipulators.registries.MobRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSpawner {
    public PacketSpawner(PacketBuffer buf) {
    }

    public PacketSpawner() {
    }

    public void toBytes(PacketBuffer buf) {
      }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();

        //Get a random block from the same chunk as sender coordinates
        int x = sender.blockPosition().getX() + (sender.getRandom().nextInt(16) - 8);
        int z = sender.blockPosition().getZ() + (sender.getRandom().nextInt(16) - 8);

        //Spawn the entity
        GraskeEntity graske = new GraskeEntity(MobRegistry.GRASKE.get(), sender.getLevel());
        sender.getLevel().addFreshEntity(graske);
        graske.moveTo(x, getTopBlock(new BlockPos(z, 255, z), sender).getY(), z);
    }

    private static BlockPos getTopBlock(BlockPos pos, ServerPlayerEntity sender) {
        BlockPos topBlock = pos;

        for (int i = 255; i > 0; i--) {
            if (sender.level.getBlockState(new BlockPos(pos.getX(), i, pos.getZ())).getBlock() != Blocks.AIR) {
                topBlock = new BlockPos(pos.getX(), i + 1, pos.getZ());
                break;
            }
        }

        return topBlock;
    }
}