package com.SpectrumFATM.vortexmanipulators.client.gui.vm;

import com.SpectrumFATM.vortexmanipulators.network.PacketBioScan;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tardis.mod.client.guis.vm.VortexMFunctionScreen;

@OnlyIn(Dist.CLIENT)
public class VMScanner extends VortexMFunctionScreen {
    private final TranslationTextComponent title = new TranslationTextComponent("gui.vm.bioscanner.title");
    private final TranslationTextComponent name = new TranslationTextComponent("gui.vm.bioscanner.name");
    private final TranslationTextComponent scan = new TranslationTextComponent("gui.vm.bioscanner.scan");
    private final TranslationTextComponent instruction = new TranslationTextComponent("gui.vm.bioscanner.instruct");

    private TextFieldWidget userInput;
    private Button scanButton;

    @Override
    public void init() {
        super.init();
        this.userInput = new TextFieldWidget(this.font, this.width / 2 - 75, this.height / 2 - 10, 150, 20, new TranslationTextComponent(""));
        this.userInput.isFocused();
        this.children.add(this.userInput);
        this.scanButton = new Button(this.width / 2 - 75, this.height / 2 + 13, 150, 20, this.scan, (onScanPress) -> {
            NetworkHandler.INSTANCE.sendToServer(new PacketBioScan(this.userInput.getValue()));
            Minecraft.getInstance().setScreen(null);
        });
        this.addButton(scanButton);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title.getString(), this.getMinX() + 135, this.getMaxY() + 10, 16777215);
        drawCenteredString(matrixStack, this.font, this.name.getString(), this.width / 2 - 90, this.height / 2 - 4, 16777215);
        drawCenteredString(matrixStack, this.font, this.instruction.getString(), this.getMinX() + 130, this.height / 2 - 20, 16777215);
        this.userInput.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
