package com.SpectrumFATM.vortexmanipulators.blocks;

import com.SpectrumFATM.vortexmanipulators.network.PacketTemporalDisplacement;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.tardis.mod.sounds.TSounds;

public class TemporalEraser extends Block {

    public TemporalEraser(Properties properties) {
        super(properties);
    }

    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
            if (worldIn.isClientSide) {
                NetworkHandler.INSTANCE.sendToServer(new PacketTemporalDisplacement(pos));
                worldIn.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), TSounds.SUBSYSTEMS_ON.get(), SoundCategory.AMBIENT, 1F, 1F, true);
            }
        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.box(0.25D, 0D, 0.25D, 0.75D, 0.625D, 0.75D);
        return shape;
    }
}
