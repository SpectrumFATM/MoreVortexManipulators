package com.SpectrumFATM.vortexmanipulators.items;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tardis.mod.constants.TardisConstants;
import net.tardis.mod.misc.IItemTooltipProvider;

import java.util.List;

public class RiftManipulator extends BlockItem implements IItemTooltipProvider {
    public RiftManipulator(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (this.hasDescriptionTooltips()) {
            tooltip.add(TardisConstants.Translations.TOOLTIP_CONTROL);
            if (Screen.hasControlDown()) {
                tooltip.clear();
                tooltip.add(0, this.getName(stack));
                this.createDescriptionTooltips(stack, worldIn, tooltip, flagIn);
            }
        }
    }

    @Override
    public void createDescriptionTooltips(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Ah but, the thing about Cardiff - it's built on a rift in time and space - just like... California on the San Andreas Fault. But the rift bleeds energy. Every now and then I need to open up the engines, soak up the energy, and us it as fuel!").withStyle(TextFormatting.GRAY));
        tooltip.add(new StringTextComponent(" \n").withStyle(TextFormatting.GRAY));
        tooltip.add(new StringTextComponent("Manipulates the rift itself. Use it to stabilise and charge your artron batteries... But be careful, time rifts are unpredictable!").withStyle(TextFormatting.GRAY));

    }

    @Override
    public boolean shouldShowTooltips() {
        return true;
    }

    @Override
    public void setShowTooltips(boolean b) {

    }

    @Override
    public boolean hasStatisticsTooltips() {
        return false;
    }

    @Override
    public void setHasStatisticsTooltips(boolean b) {

    }

    @Override
    public boolean hasDescriptionTooltips() {
        return true;
    }

    @Override
    public void setHasDescriptionTooltips(boolean b) {

    }
}
