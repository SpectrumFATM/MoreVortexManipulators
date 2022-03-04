package com.SpectrumFATM.vortexmanipulators.client.gui.vm;

import com.SpectrumFATM.vortexmanipulators.network.PacketDeleteWaypoint;
import com.SpectrumFATM.vortexmanipulators.network.PacketTeleportHandler;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.tardis.mod.client.ClientHelper;
import net.tardis.mod.client.guis.vm.VortexMFunctionScreen;
import net.tardis.mod.helper.PlayerHelper;
import net.tardis.mod.items.TItems;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class VMWaypoint extends VortexMFunctionScreen {

    private final TranslationTextComponent title = new TranslationTextComponent("gui.vm.waypoints.title");

    private Button delete;
    private Button left;
    private Button right;
    private Button enter;
    private Button add;

    private int selected = 1;
    private int maxSelected;
    private String option = "";
    private CompoundNBT nbt;
    private List<RegistryKey<World>> worldKeys = new ArrayList();
    private ItemStack mainStack = Minecraft.getInstance().player.getMainHandItem();
    private ItemStack offStack = Minecraft.getInstance().player.getOffhandItem();
    private int x;
    private int y;
    private int z;
    private String dim;

    @Override
    public void init() {
        if (mainStack.getItem() == TItems.VORTEX_MANIP.get()) {
            nbt = mainStack.getTag();
        } else if (offStack.getItem() == TItems.VORTEX_MANIP.get()){
            nbt=offStack.getTag();
        }
        this.right = new Button(this.width / 2 + 21, this.height / 2, 20, 20, new TranslationTextComponent(">"), (rightPress) -> {
            selected += 1;
            if (selected > maxSelected) {
                selected = maxSelected;
            }
        });
        this.left = new Button(this.width / 2 - 61, this.height / 2, 20, 20, new TranslationTextComponent("<"), (leftPress) -> {
            selected -= 1;
            if (selected < 1) {
                selected = 1;
            }
        });

        this.enter = new Button(this.width / 2 + 43, this.height / 2, 40, 20, new TranslationTextComponent("Enter"), (enterPress) -> {
            NetworkHandler.INSTANCE.sendToServer(new PacketTeleportHandler(selected));
        });
        this.delete = new Button(this.width / 2 - 104, this.height / 2, 40, 20, new TranslationTextComponent("Delete"), (deletePress) -> {
            nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND);
            NetworkHandler.INSTANCE.sendToServer(new PacketDeleteWaypoint(selected - 1));
            ClientHelper.openGui(null);
            PlayerHelper.closeVMModel(this.minecraft.player);
        });
        this.add = new Button(this.width / 2 + 85, this.height / 2, 20, 20, new TranslationTextComponent("+"), (addPress) -> {
            ClientHelper.openGui(new VMWaypointSave());
        });

        this.addButton(delete);
        this.addButton(left);
        this.addButton(right);
        this.addButton(enter);
        this.addButton(add);
    }

    @Override
    public void tick() {
        if (nbt != null) {
            if (nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND) != null) {
                option = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getString("name");
                maxSelected = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).toArray().length;

            }
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title.getString(), this.width / 2, this.height / 2 - 13, 16777215);
        drawCenteredString(matrixStack, this.font, option, this.width / 2 - 10, this.height / 2 + 7, 16777215);
    }


}
