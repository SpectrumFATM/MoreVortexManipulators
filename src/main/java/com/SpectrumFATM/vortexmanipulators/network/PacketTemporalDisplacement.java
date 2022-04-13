package com.SpectrumFATM.vortexmanipulators.network;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.items.TItems;

import java.util.*;
import java.util.function.Supplier;

public class PacketTemporalDisplacement {
    private BlockPos pos;

    public PacketTemporalDisplacement(PacketBuffer buf) {
        this.pos = buf.readBlockPos();
    }

    public PacketTemporalDisplacement(BlockPos pos) {
        this.pos = pos;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(this.pos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {

        ServerPlayerEntity sender = ctx.get().getSender();
        List<RegistryKey<World>> worlds = new ArrayList<>(sender.server.levelKeys());

        Random random = new Random();

        List<Entity> entities = sender.getLevel().getEntitiesOfClass(Entity.class, new AxisAlignedBB(pos).inflate(100));

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (Entity entity : entities) {

                    if (entity.getType() == EntityType.PLAYER) {
                        PlayerEntity playerEntity = (PlayerEntity) entity;
                        if (!playerEntity.inventory.contains(new ItemStack(TItems.VORTEX_MANIP.get())) && !playerEntity.isCreative()) {
                            displace(sender, entity);
                        }
                    } else {
                        displace(sender, entity);
                        }
                    }
                sender.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            }
        };

        sender.displayClientMessage(new StringTextComponent("===TE=== \nTemporal detonation occuring in 10 seconds!").withStyle(TextFormatting.BOLD).withStyle(TextFormatting.GRAY), false);
        timer.schedule(timerTask, 10000);
    }

    private void displace(ServerPlayerEntity sender, Entity entity) {
        LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, sender.level);
        lightningBoltEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
        sender.level.addFreshEntity(lightningBoltEntity);
        entity.kill();
    }
}