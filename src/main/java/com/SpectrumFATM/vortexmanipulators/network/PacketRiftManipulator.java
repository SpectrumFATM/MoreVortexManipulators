package com.SpectrumFATM.vortexmanipulators.network;

import com.SpectrumFATM.vortexmanipulators.entities.TimeFissureEntity;
import com.SpectrumFATM.vortexmanipulators.registries.MobRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.cap.Capabilities;
import net.tardis.mod.sounds.TSounds;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

public class PacketRiftManipulator {

    private BlockPos pos;
    private static Boolean isCharging = false;

    public PacketRiftManipulator(PacketBuffer buf) {
        this.pos = buf.readBlockPos();
    }

    public PacketRiftManipulator(BlockPos pos) {
        this.pos = pos;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(this.pos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        BlockPos fissurePos = generateBlockPos(this.pos, sender);

        Chunk c = sender.getLevel().getChunkAt(this.pos);

        c.getCapability(Capabilities.RIFT).ifPresent((rift) -> {
            if (rift.isRift()) {
                Float riftEnergy = rift.getRiftEnergy();

                if (riftEnergy < 100) {
                    riftFracture(sender, pos);
                } else {
                    if (shouldTimeFissureSpawn()) {
                        TimeFissureEntity timeFissure = new TimeFissureEntity(MobRegistry.FISSURE.get(), sender.getLevel());
                        sender.getLevel().addFreshEntity(timeFissure);
                        timeFissure.moveTo(fissurePos.getX(), generateBlockPos(fissurePos, sender).getY(), fissurePos.getZ());
                        sender.displayClientMessage(new StringTextComponent("Rift Manipulator Status: Malfunction! Temporal anomaly detected!").withStyle(TextFormatting.BOLD).withStyle(TextFormatting.GRAY), false);
                        sender.getLevel().playLocalSound(pos.getX(), pos.getY(), pos.getZ(), TSounds.ELECTRIC_ARC.get(), SoundCategory.AMBIENT, 1F, 1F, true);
                    } else {
                        addToRift(sender, riftEnergy, c);
                    }
                }
            } else {
                sender.displayClientMessage(new StringTextComponent("Rift Manipulator Status: No temporal rift detected!").withStyle(TextFormatting.BOLD).withStyle(TextFormatting.GRAY), false);
            }
        });
    }

    private void addToRift(ServerPlayerEntity sender, Float riftEnergy, Chunk chunk) {

        chunk.getCapability(Capabilities.RIFT).ifPresent((rift) -> {
            if (rift.isRift()) {
                if (riftEnergy < 963 && riftEnergy > 100 && !isCharging) {
                    this.isCharging = true;
                    //Rift charge potential event.
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            rift.addEnergy(963 - riftEnergy);
                            isCharging = false;
                        }
                    };

                    float energyTimer = (963 - riftEnergy) * 1000;
                    timer.schedule(timerTask, (long)energyTimer);
                    sender.displayClientMessage(new StringTextComponent("Rift Manipulator Status: Stable!").withStyle(TextFormatting.BOLD).withStyle(TextFormatting.GRAY), false);
                    sender.displayClientMessage(new StringTextComponent("Rift Manipulator Status: Charging.").withStyle(TextFormatting.BOLD).withStyle(TextFormatting.GRAY), false);
                    sender.getLevel().playLocalSound(pos.getX(), pos.getY(), pos.getZ(), TSounds.SUBSYSTEMS_ON.get(), SoundCategory.AMBIENT, 1F, 1F, true);
                } else if (this.isCharging) {
                    sender.displayClientMessage(new StringTextComponent("Rift Manipulator Status: Charging. Please wait.").withStyle(TextFormatting.BOLD).withStyle(TextFormatting.GRAY), false);
                }
            }
        });
    }
    private static Boolean shouldTimeFissureSpawn() {
        Boolean result = false;
        Random random = new Random();


        if (random.nextInt(20) == 1) {
            result = true;
        }

        return result;
    }

    private static BlockPos generateBlockPos(BlockPos pos, ServerPlayerEntity sender) {
        Random random = new Random();

        if (random.nextInt(2) == 1) {
            pos = new BlockPos(pos.getX() + random.nextInt(50), pos.getY(), pos.getZ() + random.nextInt(50));
        } else {
            pos = new BlockPos(pos.getX() - random.nextInt(50), pos.getY(), pos.getZ() - random.nextInt(50));
        }

        BlockPos spawnPos = getTopBlock(pos, sender);

        return spawnPos;
    }

    private static BlockPos[] nearbyBlocks(BlockPos pos) {
        BlockPos[] positions = new BlockPos[10];
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int positiveNegativeX = random.nextInt(2);
            int positiveNegativeZ = random.nextInt(2);

            int modifierX = random.nextInt(100);
            int modifierZ = random.nextInt(100);


            if (positiveNegativeX == 1) {
                modifierX = modifierX * -1;
            }

            if (positiveNegativeZ != 1) {
                modifierZ = modifierZ * -1;
            }

            positions[i] = new BlockPos(pos.getX() + modifierX, pos.getY(), pos.getZ() + modifierZ);
        }

        return positions;
    }

    private void riftFracture(ServerPlayerEntity sender, BlockPos pos) {
        //Rift fracture event.
        sender.displayClientMessage(new StringTextComponent("Rift Manipulator Status: Overloading!").withStyle(TextFormatting.BOLD).withStyle(TextFormatting.GRAY), false);

        BlockPos[] positions = nearbyBlocks(pos);

        for (BlockPos position : positions) {
            TimeFissureEntity timeFissure = new TimeFissureEntity(MobRegistry.FISSURE.get(), sender.getLevel());
            sender.getLevel().addFreshEntity(timeFissure);
            timeFissure.moveTo(generateBlockPos(position, sender), 0.0F, 0.0F);
        }

        sender.getLevel().setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        sender.getLevel().playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.AMBIENT, 1F, 1F, true);
    }

    private static BlockPos getTopBlock(BlockPos pos, PlayerEntity sender) {
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