package net.disjoint.uncancelledbirchforests.world.feature.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.disjoint.uncancelledbirchforests.world.feature.tree.UBFTrunkPlacerTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BirchTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<BirchTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(birchTrunkPlacerInstance ->
            fillTrunkPlacerFields(birchTrunkPlacerInstance).apply(birchTrunkPlacerInstance, BirchTrunkPlacer::new));

    public BirchTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return UBFTrunkPlacerTypes.BIRCH_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);

        int maxHeight = height + random.nextBetween(firstRandomHeight, firstRandomHeight + 3) +
                random.nextBetween(secondRandomHeight - 1, secondRandomHeight + 2);

        for (int i = 0; i < maxHeight; i++) {
            getAndSetState(world, replacer, random, startPos.up(i), config);
        }

        for (Direction direction : Direction.Type.HORIZONTAL) {
            int minBranchHeight = 2;
            int maxBranchHeight = maxHeight - 4;
            int branchHeight = random.nextBetween(minBranchHeight, maxBranchHeight);
            if (random.nextBoolean()) {
                BlockPos candidatePos = startPos.up(branchHeight).offset(direction);
                if (random.nextFloat() > 0.3f && world.testBlockState(candidatePos, AbstractBlock.AbstractBlockState::isAir) && horizontalCheck(world, candidatePos, direction)) {
                    Function<BlockState, BlockState> function = (state) -> (BlockState) state.withIfExists(PillarBlock.AXIS, direction.getAxis());
                    getAndSetState(world, replacer, random, candidatePos, config, function);
                }
            }
        }
        return ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(maxHeight), 0, false));
    }

    private boolean horizontalCheck(TestableWorld world, BlockPos pos, Direction direction) {
        return world.testBlockState(pos.offset(direction.getOpposite(), 2), blockState -> !blockState.isOf(Blocks.BIRCH_LOG)) &&
                world.testBlockState(pos.offset(direction.getOpposite()).offset(direction.rotateClockwise(Direction.Axis.Y)),blockState -> !blockState.isOf(Blocks.BIRCH_LOG)) &&
                world.testBlockState(pos.offset(direction.getOpposite()).offset(direction.rotateCounterclockwise(Direction.Axis.Y)),blockState -> !blockState.isOf(Blocks.BIRCH_LOG));
    }
}