package com.SpectrumFATM.vortexmanipulators.network;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tardis.mod.cap.Capabilities;
import net.tardis.mod.config.TConfig;
import net.tardis.mod.enums.EnumSubsystemType;
import net.tardis.mod.helper.PlayerHelper;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.TextHelper;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.items.TItems;
import net.tardis.mod.misc.SpaceTimeCoord;
import net.tardis.mod.tileentities.console.misc.DistressSignal;

import java.util.Optional;
import java.util.function.Supplier;

public class PacketShareWaypoint {

    private ResourceLocation tardisDim;
    private Long coord;
    private String destDim;

    public PacketShareWaypoint(PacketBuffer buf) {
        this.tardisDim = buf.readResourceLocation();
        this.coord = buf.readLong();
        this.destDim = buf.readUtf();
    }

    public PacketShareWaypoint(ResourceLocation tardisDim, Long coord, String destDim) {
        this.tardisDim = tardisDim;
        this.coord = coord;
        this.destDim = destDim;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeResourceLocation(tardisDim);
        buf.writeLong(coord);
        buf.writeUtf(destDim);
    }

    public static Optional<RegistryKey<World>> iterateRegistryKeys(MinecraftServer server, ResourceLocation destDim) {
        for (RegistryKey<World> registryKey : server.levelKeys()) {
            if (registryKey.location().equals(destDim)) {
                return Optional.of(registryKey);
            }
        }
        return Optional.empty();
    }

    public static World getWorldFromRegistryKey(MinecraftServer server, RegistryKey<World> registryKey) {
        return server.getLevel(registryKey);
    }

    public static World getWorldFromResourceLocation(MinecraftServer server, ResourceLocation dimensionLocation) {
        RegistryKey<World> registryKey = RegistryKey.create(Registry.DIMENSION_REGISTRY, dimensionLocation);
        return getWorldFromRegistryKey(server, registryKey);
    }

    public static void handle(PacketShareWaypoint mes, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayerEntity player = context.get().getSender();
            MinecraftServer server = player.getServer();

            if (player != null) {
                if (WorldHelper.canVMTravelToDimension(getWorldFromResourceLocation(server, ResourceLocation.tryParse(mes.destDim)))) {
                    ServerWorld world = player.getLevel();

                    TardisHelper.getConsole(server, mes.tardisDim).ifPresent(tile -> {
                        tile.getSubsystem(EnumSubsystemType.ANTENNA).ifPresent(sys -> {
                            VortexM.LOGGER.info(sys.canBeUsed(tile));

                            VortexM.LOGGER.info("SENDING!");
                            iterateRegistryKeys(server, ResourceLocation.tryParse(mes.destDim)).ifPresent(destDim -> {
                                tile.addDistressSignal(new DistressSignal("Incoming space-time coordinates!", new SpaceTimeCoord(destDim, BlockPos.of(mes.coord))));
                                tile.getLevel().getCapability(Capabilities.TARDIS_DATA).ifPresent(data -> {
                                    PlayerHelper.sendMessageToPlayer(player, TextHelper.createVortexManipMessage(new TranslationTextComponent("message.vm.locationshared")), false);
                                });
                                player.getCooldowns().addCooldown((Item) TItems.VORTEX_MANIP.get(), (Integer) TConfig.SERVER.vmCooldownTime.get() * 20);
                            });
                        });
                    });
                } else {
                    PlayerHelper.sendMessageToPlayer(player, TextHelper.createVortexManipMessage(new TranslationTextComponent("message.vm.impossible")), false);
                }
            }
        });

        context.get().setPacketHandled(true);
    }
}