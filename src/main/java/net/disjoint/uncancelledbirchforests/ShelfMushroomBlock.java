package net.disjoint.uncancelledbirchforests;

import net.disjoint.uncancelledbirchforests.util.UBFProperties;
import net.disjoint.uncancelledbirchforests.util.properties.ShelfMushroomType;
import static net.disjoint.uncancelledbirchforests.util.properties.ShelfMushroomType.*;
import static net.minecraft.util.math.Direction.DOWN;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;
import java.util.Map;

public class ShelfMushroomBlock extends Block implements Fertilizable {
    public static final EnumProperty<ShelfMushroomType> SHELF_MUSHROOM_TYPE;
    public static final Map<Direction, BooleanProperty> FACING_PROPERTIES;
    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0F, 7.0f, 0.0F, 16.0F, 9.0F, 8.0F);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(8.0F, 7.0f, 0.0F, 16.0F, 9.0F, 16.0F);
    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0F, 7.0f, 8.0F, 16.0F, 9.0F, 16.0F);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0F, 7.0f, 0.0F, 8.0F, 9.0F, 16.0F);
    public static final VoxelShape NORTH_EAST_SHAPE = Block.createCuboidShape(8.0F, 7.0f, 0.0F, 16.0F, 9.0F, 8.0F);
    public static final VoxelShape SOUTH_EAST_SHAPE = Block.createCuboidShape(8.0F, 7.0f, 8.0F, 16.0F, 9.0F, 16.0F);
    public static final VoxelShape SOUTH_WEST_SHAPE = Block.createCuboidShape(0.0F, 7.0f, 8.0F, 8.0F, 9.0F, 16.0F);
    public static final VoxelShape NORTH_WEST_SHAPE = Block.createCuboidShape(0.0F, 7.0f, 0.0F, 8.0F, 9.0F, 8.0F);

    public ShelfMushroomBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(SHELF_MUSHROOM_TYPE, NORTH_SINGLE));
    }

    private static VoxelShape getShapeForState(BlockState state) {
        ShelfMushroomType type = state.get(SHELF_MUSHROOM_TYPE);
        if (type == NORTH_STRAIGHT || type == NORTH_SINGLE) {
            return NORTH_SHAPE;
        }
        if (type == EAST_STRAIGHT || type == EAST_SINGLE) {
            return EAST_SHAPE;
        }
        if (type == SOUTH_STRAIGHT || type == SOUTH_SINGLE) {
            return SOUTH_SHAPE;
        }
        if (type == WEST_STRAIGHT || type == WEST_SINGLE) {
            return WEST_SHAPE;
        }
        if (type == NORTH_EAST) {
            return NORTH_EAST_SHAPE;
        }
        if (type == SOUTH_EAST) {
            return SOUTH_EAST_SHAPE;
        }
        if (type == SOUTH_WEST) {
            return SOUTH_WEST_SHAPE;
        }
        if (type == NORTH_WEST) {
            return NORTH_WEST_SHAPE;
        }
        else return NORTH_SHAPE;
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShapeForState(state);
    }

    protected static boolean faceCheck(Direction direction, WorldView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos.offset(direction));
        return state.isIn(BlockTags.LOGS) && Block.isFaceFullSquare(state.getCollisionShape(world, pos.offset(direction)), direction.getOpposite());
    }

    private static boolean isValidNeighbor(WorldView world, BlockPos pos, Direction thisDirection, Direction checkDirection) {
        BlockPos checkPos = pos.offset(checkDirection);
        BlockState state = world.getBlockState(checkPos);
            if (faceCheck(checkDirection, world, pos)) {
                return true;
            } else {
                if (state.isOf(UBFBlocks.SHELF_MUSHROOM)) {
                    ShelfMushroomType type = state.get(SHELF_MUSHROOM_TYPE);
                    if (thisDirection == Direction.NORTH) {
                        if (checkDirection == Direction.EAST && !faceCheck(Direction.NORTH, world, checkPos) && !faceCheck(Direction.EAST, world, checkPos)) {
                            return type.hasNorth() && type != NORTH_EAST;
                        }
                        if (checkDirection == Direction.WEST && !faceCheck(Direction.NORTH, world, checkPos) && !faceCheck(Direction.WEST, world, checkPos)) {
                            return type.hasNorth() && type != NORTH_WEST;
                        }
                        else return type.hasNorth();
                    }
                    if (thisDirection == Direction.EAST) {
                        if (checkDirection == Direction.NORTH && !faceCheck(Direction.NORTH, world, checkPos) && !faceCheck(Direction.EAST, world, checkPos)) {
                            return type.hasEast() && type != NORTH_EAST;
                        }
                        if (checkDirection == Direction.SOUTH && !faceCheck(Direction.SOUTH, world, checkPos) && !faceCheck(Direction.EAST, world, checkPos)) {
                            return type.hasEast() && type != SOUTH_EAST;
                        }
                        else return type.hasEast();
                    }
                    if (thisDirection == Direction.SOUTH) {
                        if (checkDirection == Direction.EAST && !faceCheck(Direction.SOUTH, world, checkPos) && !faceCheck(Direction.EAST, world, checkPos)) {
                            return type.hasSouth() && type != SOUTH_EAST;
                        }
                        if (checkDirection == Direction.WEST && !faceCheck(Direction.SOUTH, world, checkPos) && !faceCheck(Direction.WEST, world, checkPos)) {
                            return type.hasSouth() && type != SOUTH_WEST;
                        }
                        else return type.hasSouth();
                    }
                    if (thisDirection == Direction.WEST) {
                        if (checkDirection == Direction.NORTH && !faceCheck(Direction.NORTH, world, checkPos) && !faceCheck(Direction.WEST, world, checkPos)) {
                            return type.hasWest() && type != NORTH_WEST;
                        }
                        if (checkDirection == Direction.SOUTH && !faceCheck(Direction.SOUTH, world, checkPos) && !faceCheck(Direction.WEST, world, checkPos)) {
                            return type.hasWest() && type != SOUTH_WEST;
                        }
                        else return type.hasWest();
                    }
                }
            }
        return false;
    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return (faceCheck(Direction.NORTH, world, pos) || faceCheck(Direction.EAST, world, pos) || faceCheck(Direction.SOUTH, world, pos) || faceCheck(Direction.WEST, world, pos)) ||
                (isValidNeighbor(world, pos, Direction.NORTH, Direction.EAST) && isValidNeighbor(world, pos, Direction.EAST, Direction.NORTH)) ||
                (isValidNeighbor(world, pos, Direction.SOUTH, Direction.EAST) && isValidNeighbor(world, pos, Direction.EAST, Direction.SOUTH)) ||
                (isValidNeighbor(world, pos, Direction.SOUTH, Direction.WEST) && isValidNeighbor(world, pos, Direction.WEST, Direction.SOUTH)) ||
                (isValidNeighbor(world, pos, Direction.NORTH, Direction.WEST) && isValidNeighbor(world, pos, Direction.WEST, Direction.NORTH));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = world.getBlockState(pos);
        for (Direction direction : ctx.getPlacementDirections()) {
            if ((direction == DOWN) || (direction == Direction.UP) || !canPlaceAt(state, world, pos)) {
                return null;
            }
            else {
                if (this.canPlaceAt(state, world, pos)) {
                    return this.getDefaultState().with(SHELF_MUSHROOM_TYPE, getMushroomType(world, pos));
                }
            }
        }
        return null;
    }

    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (!canPlaceAt(state, world, pos)) {
            tickView.scheduleBlockTick(pos, this, 1);
        }
        if (!requirements(state, world, pos)) {
            tickView.scheduleBlockTick(pos, this, 1);
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
        if (!requirements(state, world, pos) && state.canPlaceAt(world, pos)) {
            world.setBlockState(pos, state.with(SHELF_MUSHROOM_TYPE, getMushroomType(world, pos)));
        }
    }

    public static ShelfMushroomType getMushroomType(WorldView world, BlockPos pos) {
        Direction north = Direction.NORTH;
        Direction east = Direction.EAST;
        Direction south = Direction.SOUTH;
        Direction west = Direction.WEST;
        if (faceCheck(north, world, pos)) {
            if (isValidNeighbor(world, pos, north, east) && isValidNeighbor(world, pos, north, west)) {
                return NORTH_STRAIGHT;
            }
            if (!isValidNeighbor(world, pos, north, east) && !isValidNeighbor(world, pos, north, west)) {
                return NORTH_SINGLE;
            }
        }
        if (faceCheck(east, world, pos)) {
            if (isValidNeighbor(world, pos, east, north) && isValidNeighbor(world, pos, east, south)) {
                return EAST_STRAIGHT;
            }
            if (!isValidNeighbor(world, pos, east, north) && !isValidNeighbor(world, pos, east, south)) {
                return EAST_SINGLE;
            }
        }
        if (faceCheck(south, world, pos)) {
            if (isValidNeighbor(world, pos, south, east) && isValidNeighbor(world, pos, south, west)) {
                return SOUTH_STRAIGHT;
            }
            if (!isValidNeighbor(world, pos, south, east) && !isValidNeighbor(world, pos, south, west)) {
                return SOUTH_SINGLE;
            }
        }
        if (faceCheck(west, world, pos)) {
            if (isValidNeighbor(world, pos, west, north) && isValidNeighbor(world, pos, west, south)) {
                return WEST_STRAIGHT;
            }
            if (!isValidNeighbor(world, pos, west, north) && !isValidNeighbor(world, pos, west, south)) {
                return WEST_SINGLE;
            }
        }
        if ((faceCheck(north, world, pos) && isValidNeighbor(world, pos, north, east) && !isValidNeighbor(world, pos, north, west)) ||
                (faceCheck(east, world, pos) && isValidNeighbor(world, pos, east, north) && !isValidNeighbor(world, pos, east, south))) {
            return NORTH_EAST;
        }
        else if (world.getBlockState(pos.offset(north)).isOf(UBFBlocks.SHELF_MUSHROOM) && isValidNeighbor(world, pos, east, north) &&
                world.getBlockState(pos.offset(east)).isOf(UBFBlocks.SHELF_MUSHROOM) && isValidNeighbor(world, pos, north, east)) {
            return NORTH_EAST;
        }
        if ((faceCheck(east, world, pos) && isValidNeighbor(world, pos, east, south) && !isValidNeighbor(world, pos, east, north)) ||
                (faceCheck(south, world, pos) && isValidNeighbor(world, pos, south, east) && !isValidNeighbor(world, pos, south, west))) {
            return SOUTH_EAST;
        }
        else if (world.getBlockState(pos.offset(east)).isOf(UBFBlocks.SHELF_MUSHROOM) && isValidNeighbor(world, pos, south, east) &&
                world.getBlockState(pos.offset(south)).isOf(UBFBlocks.SHELF_MUSHROOM) && isValidNeighbor(world, pos, east, south)) {
            return SOUTH_EAST;
        }
        if ((faceCheck(south, world, pos) && isValidNeighbor(world, pos, south, west) && !isValidNeighbor(world, pos, south, east)) ||
                (faceCheck(west, world, pos) && isValidNeighbor(world, pos, west, south) && !isValidNeighbor(world, pos, west, north))) {
            return SOUTH_WEST;
        }
        else if (world.getBlockState(pos.offset(south)).isOf(UBFBlocks.SHELF_MUSHROOM) && isValidNeighbor(world, pos, west, south) &&
                world.getBlockState(pos.offset(west)).isOf(UBFBlocks.SHELF_MUSHROOM) && isValidNeighbor(world, pos, south, west)) {
            return SOUTH_WEST;
        }
        if ((faceCheck(west, world, pos) && isValidNeighbor(world, pos, west, north) && !isValidNeighbor(world, pos, west, south)) ||
                (faceCheck(north, world, pos) && isValidNeighbor(world, pos, north, west) && !isValidNeighbor(world, pos, north, east))) {
            return NORTH_WEST;
        }
        else if (world.getBlockState(pos.offset(west)).isOf(UBFBlocks.SHELF_MUSHROOM) && isValidNeighbor(world, pos, north, west) &&
                world.getBlockState(pos.offset(north)).isOf(UBFBlocks.SHELF_MUSHROOM) && isValidNeighbor(world, pos, west, north)) {
            return NORTH_WEST;
        }
        return null;
    }

    private static boolean requirements(BlockState state, WorldView world, BlockPos pos) {
        ShelfMushroomType type = state.get(SHELF_MUSHROOM_TYPE);
        if (type == NORTH_SINGLE) {
            return faceCheck(Direction.NORTH, world, pos) && !isValidNeighbor(world, pos, Direction.NORTH, Direction.EAST) && !isValidNeighbor(world, pos, Direction.NORTH, Direction.WEST);
        }
        if (type == NORTH_STRAIGHT) {
        return faceCheck(Direction.NORTH, world, pos) && isValidNeighbor(world, pos, Direction.NORTH, Direction.EAST) && isValidNeighbor(world, pos, Direction.NORTH, Direction.WEST);
        }
        if (type == EAST_SINGLE) {
            return faceCheck(Direction.EAST, world, pos) && !isValidNeighbor(world, pos, Direction.EAST, Direction.NORTH) && !isValidNeighbor(world, pos, Direction.EAST, Direction.SOUTH);
        }
        if (type == EAST_STRAIGHT) {
            return faceCheck(Direction.EAST, world, pos) && isValidNeighbor(world, pos, Direction.EAST, Direction.NORTH) && isValidNeighbor(world, pos, Direction.EAST, Direction.SOUTH);
        }
        if (type == SOUTH_SINGLE) {
            return faceCheck(Direction.SOUTH, world, pos) && !isValidNeighbor(world, pos, Direction.SOUTH, Direction.EAST) && !isValidNeighbor(world, pos, Direction.SOUTH, Direction.WEST);
        }
        if (type == SOUTH_STRAIGHT) {
            return faceCheck(Direction.SOUTH, world, pos) && isValidNeighbor(world, pos, Direction.SOUTH, Direction.EAST) && isValidNeighbor(world, pos, Direction.SOUTH, Direction.WEST);
        }
        if (type == WEST_SINGLE) {
            return faceCheck(Direction.WEST, world, pos) && !isValidNeighbor(world, pos, Direction.WEST, Direction.NORTH) && !isValidNeighbor(world, pos, Direction.WEST, Direction.SOUTH);
        }
        if (type == WEST_STRAIGHT) {
            return faceCheck(Direction.WEST, world, pos) && isValidNeighbor(world, pos, Direction.WEST, Direction.NORTH) && isValidNeighbor(world, pos, Direction.WEST, Direction.SOUTH);
        }
        if (type == NORTH_EAST) {
            if (faceCheck(Direction.NORTH, world, pos)) {
                return isValidNeighbor(world, pos, Direction.NORTH, Direction.EAST) && !isValidNeighbor(world, pos, Direction.NORTH, Direction.WEST);
            }
            else if (faceCheck(Direction.EAST, world, pos)) {
                return isValidNeighbor(world, pos, Direction.EAST, Direction.NORTH) && !isValidNeighbor(world, pos, Direction.EAST, Direction.SOUTH);
            }
            else return isValidNeighbor(world, pos, Direction.NORTH, Direction.EAST) && isValidNeighbor(world, pos, Direction.EAST, Direction.NORTH);
        }
        if (type == SOUTH_EAST) {
            if (faceCheck(Direction.SOUTH, world, pos)) {
                return isValidNeighbor(world, pos, Direction.SOUTH, Direction.EAST) && !isValidNeighbor(world, pos, Direction.SOUTH, Direction.WEST);
            }
            else if (faceCheck(Direction.EAST, world, pos)) {
                return isValidNeighbor(world, pos, Direction.EAST, Direction.SOUTH) && !isValidNeighbor(world, pos, Direction.EAST, Direction.NORTH);
            }
            else return isValidNeighbor(world, pos, Direction.SOUTH, Direction.EAST) && isValidNeighbor(world, pos, Direction.EAST, Direction.SOUTH);
        }
        if (type == SOUTH_WEST) {
            if (faceCheck(Direction.SOUTH, world, pos)) {
                return isValidNeighbor(world, pos, Direction.SOUTH, Direction.WEST) && !isValidNeighbor(world, pos, Direction.SOUTH, Direction.EAST);
            }
            else if (faceCheck(Direction.WEST, world, pos)) {
                return isValidNeighbor(world, pos, Direction.WEST, Direction.SOUTH) && !isValidNeighbor(world, pos, Direction.WEST, Direction.NORTH);
            }
            else return isValidNeighbor(world, pos, Direction.SOUTH, Direction.WEST) && isValidNeighbor(world, pos, Direction.WEST, Direction.SOUTH);
        }
        if (type == NORTH_WEST) {
            if (faceCheck(Direction.NORTH, world, pos)) {
                return isValidNeighbor(world, pos, Direction.NORTH, Direction.WEST) && !isValidNeighbor(world, pos, Direction.NORTH, Direction.EAST);
            }
            else if (faceCheck(Direction.WEST, world, pos)) {
                return isValidNeighbor(world, pos, Direction.WEST, Direction.NORTH) && !isValidNeighbor(world, pos, Direction.WEST, Direction.SOUTH);
            }
            else return isValidNeighbor(world, pos, Direction.NORTH, Direction.WEST) && isValidNeighbor(world, pos, Direction.WEST, Direction.NORTH);
        }
        else return false;
    }

    private BlockPos findSpreadSpace(WorldView world, BlockPos pos) {
        BlockPos candidatePosNorth = pos.north();
        BlockPos candidatePosEast = pos.east();
        BlockPos candidatePosSouth = pos.south();
        BlockPos candidatePosWest = pos.west();
        ShelfMushroomType type = world.getBlockState(pos).get(SHELF_MUSHROOM_TYPE);
        if (type.hasNorth() && canPlaceAt(world.getBlockState(candidatePosEast), world, candidatePosEast) && world.getBlockState(candidatePosEast).isAir()) {
            return candidatePosEast;
        }
        else if (type.hasNorth() && canPlaceAt(world.getBlockState(candidatePosWest), world, candidatePosWest) && world.getBlockState(candidatePosWest).isAir()) {
            return candidatePosWest;
        }
        else if (type.hasEast() && canPlaceAt(world.getBlockState(candidatePosSouth), world, candidatePosSouth) && world.getBlockState(candidatePosSouth).isAir()) {
            return candidatePosSouth;
        }
        else if (type.hasEast() && canPlaceAt(world.getBlockState(candidatePosNorth), world, candidatePosNorth) && world.getBlockState(candidatePosNorth).isAir()) {
            return candidatePosNorth;
        }
        else if (type.hasSouth() && canPlaceAt(world.getBlockState(candidatePosWest), world, candidatePosWest) && world.getBlockState(candidatePosWest).isAir()) {
            return candidatePosWest;
        }
        else if (type.hasSouth() && canPlaceAt(world.getBlockState(candidatePosEast), world, candidatePosEast) && world.getBlockState(candidatePosEast).isAir()) {
            return candidatePosEast;
        }
        else if (type.hasWest() && canPlaceAt(world.getBlockState(candidatePosNorth), world, candidatePosNorth) && world.getBlockState(candidatePosNorth).isAir()) {
            return candidatePosNorth;
        }
        else if (type.hasWest() && canPlaceAt(world.getBlockState(candidatePosSouth), world, candidatePosSouth) && world.getBlockState(candidatePosSouth).isAir()) {
            return candidatePosSouth;
        }
        return null;
    }

    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextDouble() < 0.1) {
            if (findSpreadSpace(world, pos) != null) {
                BlockPos spreadPos = findSpreadSpace(world, pos);
                if (world.getBlockState(spreadPos).isAir() && canPlaceAt(world.getBlockState(spreadPos), world, spreadPos)) {
                    if (world.getLightLevel(pos) < 10 && world.getLightLevel(spreadPos) < 10) {
                        world.setBlockState(spreadPos, UBFBlocks.SHELF_MUSHROOM.getDefaultState().with(SHELF_MUSHROOM_TYPE, getMushroomType(world, spreadPos)));
                    }
                }
            }
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return findSpreadSpace(world, pos) != null;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return isFertilizable(world, pos, state);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos spreadPos = findSpreadSpace(world, pos);
        world.setBlockState(spreadPos, UBFBlocks.SHELF_MUSHROOM.getDefaultState().with(SHELF_MUSHROOM_TYPE, getMushroomType(world, spreadPos)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHELF_MUSHROOM_TYPE);
    }
    static {
        SHELF_MUSHROOM_TYPE = UBFProperties.SHELF_MUSHROOM_TYPE;
        FACING_PROPERTIES = ConnectingBlock.FACING_PROPERTIES.entrySet().stream().filter((entry) -> entry.getKey() != Direction.DOWN).collect(Util.toMap());
    }
}
