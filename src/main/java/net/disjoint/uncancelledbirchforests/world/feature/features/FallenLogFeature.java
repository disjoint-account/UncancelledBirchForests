package net.disjoint.uncancelledbirchforests.world.feature.features;

import com.mojang.serialization.Codec;
import net.disjoint.uncancelledbirchforests.UBFBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FallenLogFeature<D> extends Feature<DefaultFeatureConfig> {
    public FallenLogFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();

        int length = random.nextBetween(5, 8);
        boolean bl = random.nextBoolean();
        for (int i = 0; i < length; i++) {
            Direction direction;
            if (bl) {
                direction = Direction.NORTH;
            }
            else {
                direction = Direction.EAST;
            }
            if (hasValidBase(world, pos.offset(direction, i))) {
                world.setBlockState(pos.offset(direction, i), UBFBlocks.HOLLOW_BIRCH_LOG.getDefaultState().with(PillarBlock.AXIS, direction.getAxis()), 0);
                return true;
            } else if (hasValidBase(world, pos.offset(direction.getOpposite(), i))) {
                world.setBlockState(pos.offset(direction.getOpposite(), i), UBFBlocks.HOLLOW_BIRCH_LOG.getDefaultState().with(PillarBlock.AXIS, direction.getAxis()), 0);
                return true;
            }
        }
        return false;
    }

    private boolean hasValidBase(StructureWorldAccess world, BlockPos pos) {
        return Block.isFaceFullSquare(world.getBlockState(pos.down()).getCollisionShape(world, pos.down()), Direction.UP) && world.getBlockState(pos).isAir();
    }
}
