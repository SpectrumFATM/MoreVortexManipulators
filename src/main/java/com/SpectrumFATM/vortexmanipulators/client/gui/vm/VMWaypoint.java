package com.SpectrumFATM.vortexmanipulators.client.gui.vm;

import com.SpectrumFATM.vortexmanipulators.VortexM;
import com.SpectrumFATM.vortexmanipulators.network.PacketDeleteWaypoint;
import com.SpectrumFATM.vortexmanipulators.network.PacketTeleportHandler;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.tardis.mod.client.guis.vm.VortexMFunctionScreen;
import net.tardis.mod.items.TItems;

import java.util.ArrayList;
import java.util.List;

public class VMWaypoint extends VortexMFunctionScreen {

    private final TranslationTextComponent title = new TranslationTextComponent("gui.vm.waypoints.title");

    private TextFieldWidget saveName;
    private Button delete;
    private Button left;
    private Button right;
    private Button enter;
    private Button add;

    private int selected = 1;
    private int maxSelected;
    private String option = "";
    private ClientPlayerEntity playerEntity = Minecraft.getInstance().player;
    private CompoundNBT nbt;
    private RegistryKey<World> selectedWorld;
    private List<RegistryKey<World>> worldKeys = new ArrayList();
    private int x;
    private int y;
    private int z;
    private String dim;

    @Override
    public void tick() {
        ItemStack mainStack = Minecraft.getInstance().player.getMainHandItem();
        ItemStack offStack = Minecraft.getInstance().player.getOffhandItem();
        if (mainStack.getItem() == TItems.VORTEX_MANIP.get()) {
            nbt = mainStack.getTag();
            if (nbt != null) {
                if (nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND) != null) {
                    option = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getString("name");
                    maxSelected = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).toArray().length;
                    x = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getInt("x");
                    y = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getInt("y");
                    z = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getInt("z");
                    dim = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getString("dim");
                }
            }
        } else if (offStack.getItem() == TItems.VORTEX_MANIP.get()) {
            nbt = offStack.getTag();
            if (nbt != null) {
                if (nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND) != null) {
                    option = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getString("name");
                    maxSelected = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).toArray().length;
                    x = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getInt("x");
                    y = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getInt("y");
                    z = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getInt("z");
                    dim = nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND).getCompound(selected - 1).getString("dim");
                }
            }
        }
    }

    @Override
    public void init() {
        this.saveName = new TextFieldWidget(this.font, this.width / 2 - 75, this.height / 2 - 10, 150, 20, new TranslationTextComponent(""));
        this.saveName.isFocused();
        this.saveName.setTextColor(16777215);
        this.children.add(this.saveName);
        this.right = new Button(this.width / 2 + 21, this.height / 2, 20, 20, new TranslationTextComponent(">"), (p_213031_1_) -> {
            selected += 1;
            if (selected > maxSelected) {
                selected = maxSelected;
            }
            VortexM.LOGGER.info(selected);
        });
        this.left = new Button(this.width / 2 - 61, this.height / 2, 20, 20, new TranslationTextComponent("<"), (p_213031_1_) -> {
            selected -= 1;
            if (selected < 1) {
                selected = 1;
            }
            VortexM.LOGGER.info(selected);
        });

        this.enter = new Button(this.width / 2 + 43, this.height / 2, 40, 20, new TranslationTextComponent("Enter"), (onPress) -> {
            NetworkHandler.INSTANCE.sendToServer(new PacketTeleportHandler(dim, x, y, z));
        });
        this.delete = new Button(this.width / 2 - 104, this.height / 2, 40, 20, new TranslationTextComponent("Delete"), (onPress) -> {
            if (nbt.getList("waypoints", Constants.NBT.TAG_COMPOUND) != null) {
                NetworkHandler.INSTANCE.sendToServer(new PacketDeleteWaypoint(selected - 1));
                Minecraft.getInstance().setScreen(null);
            }
        });
        this.add = new Button(this.width / 2 + 85, this.height / 2, 20, 20, new TranslationTextComponent("+"), (onPress) -> {
            Minecraft.getInstance().setScreen(new VMWaypointSave());
        });

        this.addButton(delete);
        this.addButton(left);
        this.addButton(right);
        this.addButton(enter);
        this.addButton(add);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, this.title.getString(), this.width / 2, this.height / 2 - 13, 16777215);
        drawCenteredString(matrixStack, this.font, option, this.width / 2 - 10, 125, 16777215);
    }


}
