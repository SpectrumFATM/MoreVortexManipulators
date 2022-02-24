package com.SpectrumFATM.vortexmanipulators.client.gui.vm;

import com.SpectrumFATM.vortexmanipulators.network.PacketSaveWaypoint;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.TranslationTextComponent;
import net.tardis.mod.client.guis.vm.VortexMFunctionScreen;

public class VMWaypointSave extends VortexMFunctionScreen {

    private final TranslationTextComponent title = new TranslationTextComponent("gui.vm.waypointsave.title");

    private TextFieldWidget name;
    private Button save;

    private String dimText;
    private String xPos;
    private String zPos;
    private String yPos;


    @Override
    public void init() {
        super.init();
        this.dimText = "Dimension: " + this.minecraft.player.level.dimension().location();
        this.xPos = "X: " + (int)this.minecraft.player.getX();
        this.zPos = "Y: " + (int)this.minecraft.player.getY();
        this.yPos = "Z: " + (int)this.minecraft.player.getZ();
        this.name = new TextFieldWidget(this.font, this.width / 2 - 75, this.height / 2 + 10, 150, 20, new TranslationTextComponent(""));
        this.name.setFocus(true);
        this.save = new Button(this.width / 2 - 75, this.height / 2 + 35, 150, 20, new TranslationTextComponent("Save"), (p_onPress_1_) -> {
            NetworkHandler.INSTANCE.sendToServer(new PacketSaveWaypoint(this.minecraft.player.level.dimension().location().toString(), this.name.getValue()));
            Minecraft.getInstance().setScreen(null);
        });
        this.addButton(save);
        this.children.add(this.name);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.name.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title.getString(), this.getMinX() + 135, this.getMaxY() + 10, 16777215);
        drawCenteredString(matrixStack, this.font, dimText, this.width / 2, this.height / 2 - 5, 16777215);
        drawCenteredString(matrixStack, this.font, zPos, this.width / 2, this.height / 2 - 15, 16777215);
        drawCenteredString(matrixStack, this.font, yPos, this.width / 2, this.height / 2 - 25, 16777215);
        drawCenteredString(matrixStack, this.font, xPos, this.width / 2, this.height / 2 - 35, 16777215);
        drawCenteredString(matrixStack, this.font, "Name: ", this.width / 2 - 75 - this.font.width("Name: ") / 2, this.height / 2 + 15, 16777215);
    }
}
