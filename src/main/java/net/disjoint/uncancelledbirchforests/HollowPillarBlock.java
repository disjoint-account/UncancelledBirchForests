package net.disjoint.uncancelledbirchforests;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class HollowPillarBlock extends PillarBlock {
    private static final VoxelShape SHAPE1 = Block.createCuboidShape(0, 0, 0, 16, 16, 2);
    private static final VoxelShape SHAPE2 = Block.createCuboidShape(0, 0, 2, 2, 16, 14);
    private static final VoxelShape SHAPE3 = Block.createCuboidShape(14, 0, 2, 16, 16, 14);
    private static final VoxelShape SHAPE4 = Block.createCuboidShape(0, 0, 14, 16, 16, 16);
    private static final VoxelShape YSHAPE = VoxelShapes.union(SHAPE1, SHAPE2, SHAPE3, SHAPE4);

    private static final VoxelShape SHAPE5 = Block.createCuboidShape(0, 0, 0, 16, 16, 2);
    private static final VoxelShape SHAPE6 = Block.createCuboidShape(0, 0, 2, 16, 2, 14);
    private static final VoxelShape SHAPE7 = Block.createCuboidShape(0, 14, 2, 16, 16, 14);
    private static final VoxelShape SHAPE8 = Block.createCuboidShape(0, 0, 14, 16, 16, 16);
    private static final VoxelShape XSHAPE = VoxelShapes.union(SHAPE5, SHAPE6, SHAPE7, SHAPE8);

    private static final VoxelShape SHAPE9 = Block.createCuboidShape(0, 0, 0, 2, 16, 16);
    private static final VoxelShape SHAPE10 = Block.createCuboidShape(2, 0, 0, 14, 2, 16);
    private static final VoxelShape SHAPE11 = Block.createCuboidShape(2, 14, 0, 14, 16, 16);
    private static final VoxelShape SHAPE12 = Block.createCuboidShape(14, 0, 0, 16, 16, 16);
    private static final VoxelShape ZSHAPE = VoxelShapes.union(SHAPE9, SHAPE10, SHAPE11, SHAPE12);

    public HollowPillarBlock(Settings settings) {
        super(settings);
    }

    private VoxelShape getShapeForState(BlockState state) {
        Direction.Axis axis = state.get(AXIS);
        if (axis == Direction.Axis.X) {
            return XSHAPE;
        }
        if (axis == Direction.Axis.Y) {
            return YSHAPE;
        }
        else return ZSHAPE;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShapeForState(state);
    }
}