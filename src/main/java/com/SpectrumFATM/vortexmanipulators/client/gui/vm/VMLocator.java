package com.SpectrumFATM.vortexmanipulators.client.gui.vm;

import com.SpectrumFATM.vortexmanipulators.network.PacketLocator;
import com.SpectrumFATM.vortexmanipulators.network.PacketTeleportHandler;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.client.guis.vm.VortexMFunctionScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class VMLocator extends VortexMFunctionScreen {

    private final TranslationTextComponent title = new TranslationTextComponent("gui.vm.locator.title");

    private Button left;
    private Button right;
    private Button enter;

    private int selected = 1;
    private int maxSelected;
    private String option = "";

    private final List<Map.Entry<RegistryKey<Structure<?>>, Structure<?>>> structures = new ArrayList<>(ForgeRegistries.STRUCTURE_FEATURES.getEntries());

    @Override
    public void init() {
        this.right = new Button(this.width / 2 + 21, this.height / 2, 20, 20, new TranslationTextComponent(">"), (rightPress) -> {
            selected += 1;
            if (selected > maxSelected) {
                selected = maxSelected;
            }
        });
        this.left = new Button(this.width / 2 - 81, this.height / 2, 20, 20, new TranslationTextComponent("<"), (leftPress) -> {
            selected -= 1;
            if (selected < 1) {
                selected = 1;
            }
        });

        this.enter = new Button(this.width / 2 + 43, this.height / 2, 40, 20, new TranslationTextComponent("Enter"), (enterPress) -> {
            NetworkHandler.INSTANCE.sendToServer(new PacketLocator(structures.get(selected).getValue().getRegistryName().toString()));
            Minecraft.getInstance().setScreen(null);
        });
        this.addButton(left);
        this.addButton(right);
        this.addButton(enter);
    }

    @Override
    public void tick() {
        option = structures.get(selected).getValue().getFeatureName();
        maxSelected = structures.size() - 1;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title.getString(), this.width / 2, this.height / 2 - 13, 16777215);
        drawCenteredString(matrixStack, this.font, option, this.width / 2 - 20, this.height / 2 + 7, 16777215);
    }
}
