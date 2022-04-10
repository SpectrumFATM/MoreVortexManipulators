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
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.tardis.mod.sounds.TSounds;

import java.util.Random;

public class TemporalEraser extends Block {

    Random random = new Random();

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
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.2375, 0.075, 0.462771875, 0.3, 0.55, 0.537228125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.24375, 0.56875, 0.481521875, 0.28125, 0.60625, 0.519021875), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.23125, 0.44375, 0.481521875, 0.26875, 0.48125, 0.519021875), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.23125, 0.44375, 0.481521875, 0.26875, 0.48125, 0.519021875), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.23125, 0.14375, 0.480978125, 0.26875, 0.18125, 0.518478125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.23125, 0.14375, 0.480978125, 0.26875, 0.18125, 0.518478125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.24375, 0.56875, 0.481521875, 0.28125, 0.60625, 0.519021875), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.24375, 0.01875, 0.481521875, 0.28125, 0.05625, 0.519021875), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.24375, 0.01875, 0.481521875, 0.28125, 0.05625, 0.519021875), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.480978125, 0.56875, 0.24375, 0.518478125, 0.60625, 0.28125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.480978125, 0.01875, 0.24375, 0.518478125, 0.05625, 0.28125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.481521875, 0.56875, 0.71875, 0.519021875, 0.60625, 0.75625), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.481521875, 0.01875, 0.71875, 0.519021875, 0.05625, 0.75625), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.2375, 0.075, 0.462771875, 0.7375, 0.55, 0.537228125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.450271875, 0, 0.25, 0.549728125, 0.625, 0.75), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.450271875, 0, 0.25, 0.549728125, 0.625, 0.75), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.450271875, 0, 0.25, 0.549728125, 0.625, 0.75), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.450271875, 0, 0.25, 0.549728125, 0.625, 0.75), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.25, 0, 0.450271875, 0.75, 0.625, 0.549728125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.25, 0, 0.450271875, 0.75, 0.625, 0.549728125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.25, 0, 0.450271875, 0.75, 0.625, 0.549728125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.25, 0, 0.450271875, 0.75, 0.625, 0.549728125), IBooleanFunction.OR);

        return shape;
    }
}
