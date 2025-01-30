package net.disjoint.uncancelledbirchforests.world.feature.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UBFBeehiveTreeDecorator extends TreeDecorator {
    public static final MapCodec<UBFBeehiveTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(UBFBeehiveTreeDecorator::new, decorator -> decorator.probability);
    private final float probability;

    public UBFBeehiveTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return UBFTreeDecoratorType.UBF_BEEHIVE;
    }

    @Override
    public void generate(TreeDecorator.Generator generator) {
        List<BlockPos> list = generator.getLeavesPositions();
        List<BlockPos> list2 = generator.getLogPositions();
        if (!list2.isEmpty()) {
            Random random = generator.getRandom();
            if (!(random.nextFloat() >= this.probability)) {
                int maxHeight = list.getFirst().getY() - 2;
                int minHeight = list2.getFirst().getY();
                for (Direction direction : Direction.Type.HORIZONTAL) {
                    if (random.nextFloat() <= 0.25F) {
                        List<BlockPos> list3 = list2.stream().filter(pos -> pos.getY() <= maxHeight && pos.getY() >= minHeight &&
                                generator.isAir(pos.offset(direction, 1)) && generator.isAir(pos.offset(direction, 2)) &&
                                generator.matches(pos.offset(direction).up(1), state -> state.isOf(Blocks.BIRCH_LOG))).toList();
                        List<BlockPos> list4 = new ArrayList<>(list3);
                        Util.shuffle(list4, random);
                        Optional<BlockPos> optional = list4.stream().findFirst();
                        if (optional.isPresent()) {
                            generator.replace(optional.get().offset(direction), Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING, direction));
                            generator.getWorld().getBlockEntity(optional.get().offset(direction), BlockEntityType.BEEHIVE).ifPresent(blockEntity -> {
                                int ix = 2 + random.nextInt(2);
                                for (int j = 0; j < ix; j++) {
                                    blockEntity.addBee(BeehiveBlockEntity.BeeData.create(random.nextInt(599)));
                                }
                            });
                        }
                    }
                }
            }
        }
    }
}
