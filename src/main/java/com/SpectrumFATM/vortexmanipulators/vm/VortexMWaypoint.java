package com.SpectrumFATM.vortexmanipulators.vm;

import com.SpectrumFATM.vortexmanipulators.client.gui.vm.VMWaypoint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.tardis.mod.vm.BaseVortexMFunction;

public class VortexMWaypoint extends BaseVortexMFunction {

    private int maxSelected;
    private ClientPlayerEntity playerEntity = Minecraft.getInstance().player;
    private CompoundNBT nbt;
    private String name = "";

    public VortexMWaypoint() {
    }

    @Override
    public void sendActionOnClient(World world, PlayerEntity player) {
        super.sendActionOnClient(world, player);

        ItemStack mainStack = Minecraft.getInstance().player.getMainHandItem();
        nbt = mainStack.getTag();

        Minecraft.getInstance().setScreen(new VMWaypoint());
    }
}
