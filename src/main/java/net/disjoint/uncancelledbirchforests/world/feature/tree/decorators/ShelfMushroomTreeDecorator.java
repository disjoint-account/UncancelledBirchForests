package net.disjoint.uncancelledbirchforests.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.disjoint.uncancelledbirchforests.ShelfMushroomBlock;
import net.disjoint.uncancelledbirchforests.UBFBlocks;
import net.disjoint.uncancelledbirchforests.util.properties.ShelfMushroomType;
import net.disjoint.uncancelledbirchforests.world.feature.tree.UBFTreeDecoratorType;
import net.minecraft.block.Blocks;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShelfMushroomTreeDecorator extends TreeDecorator {
    public static final MapCodec<ShelfMushroomTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(ShelfMushroomTreeDecorator::new, decorator -> decorator.probability);
    private final float probability;

    public ShelfMushroomTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return UBFTreeDecoratorType.SHELF_MUSHROOM;
    }

    @Override
    public void generate(TreeDecorator.Generator generator) {
        List<BlockPos> leavesList = generator.getLeavesPositions();
        List<BlockPos> logsList = generator.getLogPositions();
        if (!logsList.isEmpty()) {
            Random random = generator.getRandom();
            if (!(random.nextFloat() >= this.probability)) {
                int maxHeight = leavesList.getFirst().getY() - 2;
                int minHeight = logsList.getFirst().getY();
                for (Direction direction : Direction.Type.HORIZONTAL) {
                    if (random.nextFloat() <= 0.25F) {
                        List<BlockPos> list = logsList.stream().filter(pos -> pos.getY() <= maxHeight && pos.getY() >= minHeight &&
                                spaceCheck(generator, pos, direction) &&
                                generator.matches(pos.up(), state -> state.isOf(Blocks.BIRCH_LOG))).toList();
                        List<BlockPos> candidateList = new ArrayList<>(list);
                        Util.shuffle(candidateList, random);
                        Optional<BlockPos> optional = candidateList.stream().findFirst();
                        if (optional.isPresent()) {
                            if (random.nextBoolean()) {
                                if (spaceCheck(generator, optional.get(), direction.rotateClockwise(Direction.Axis.Y)) &&
                                        spaceCheck(generator, optional.get().offset(direction), direction.rotateClockwise(Direction.Axis.Y)) &&
                                        generator.isAir(optional.get().offset(direction, 2).offset(direction.rotateClockwise(Direction.Axis.Y), 2))) {
                                    generator.replace(optional.get().offset(direction), UBFBlocks.SHELF_MUSHROOM.getDefaultState().with(ShelfMushroomBlock.SHELF_MUSHROOM_TYPE, getType(true, direction, 0)));
                                    generator.replace(optional.get().offset(direction.rotateClockwise(Direction.Axis.Y)), UBFBlocks.SHELF_MUSHROOM.getDefaultState().with(ShelfMushroomBlock.SHELF_MUSHROOM_TYPE, getType(true, direction, 1)));
                                    generator.replace(optional.get().offset(direction).offset(direction.rotateClockwise(Direction.Axis.Y)), UBFBlocks.SHELF_MUSHROOM.getDefaultState().with(ShelfMushroomBlock.SHELF_MUSHROOM_TYPE, getType(true, direction, 2)));
                                }
                            } else {
                                generator.replace(optional.get().offset(direction), UBFBlocks.SHELF_MUSHROOM.getDefaultState().with(ShelfMushroomBlock.SHELF_MUSHROOM_TYPE, getType(false, direction, 0)));
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean spaceCheck(TreeDecorator.Generator generator, BlockPos pos, Direction direction) {
        return generator.isAir(pos.offset(direction, 1)) && generator.isAir(pos.offset(direction, 2));
    }

    private ShelfMushroomType getType(boolean bl, Direction direction, int i) {
        if (!bl) {
            if (direction == Direction.NORTH) {
                return ShelfMushroomType.SOUTH_SINGLE;
            }
            if (direction == Direction.EAST) {
                return ShelfMushroomType.WEST_SINGLE;
            }
            if (direction == Direction.SOUTH) {
                return ShelfMushroomType.NORTH_SINGLE;
            }
            if (direction == Direction.WEST) {
                return ShelfMushroomType.EAST_SINGLE;
            }
        } else {
            if (direction == Direction.NORTH) {
                if (i == 0) {
                    return (ShelfMushroomType.SOUTH_EAST);
                }
                if (i == 1) {
                    return (ShelfMushroomType.NORTH_WEST);
                }
                if (i == 2) {
                    return (ShelfMushroomType.SOUTH_WEST);
                }
            }
            if (direction == Direction.EAST) {
                if (i == 0) {
                    return (ShelfMushroomType.SOUTH_WEST);
                }
                if (i == 1) {
                    return (ShelfMushroomType.NORTH_EAST);
                }
                if (i == 2) {
                    return (ShelfMushroomType.NORTH_WEST);
                }
            }
            if (direction == Direction.SOUTH) {
                if (i == 0) {
                    return (ShelfMushroomType.NORTH_WEST);
                }
                if (i == 1) {
                    return (ShelfMushroomType.SOUTH_EAST);
                }
                if (i == 2) {
                    return (ShelfMushroomType.NORTH_EAST);
                }
            }
            if (direction == Direction.WEST) {
                if (i == 0) {
                    return (ShelfMushroomType.NORTH_EAST);
                }
                if (i == 1) {
                    return (ShelfMushroomType.SOUTH_WEST);
                }
                if (i == 2) {
                    return (ShelfMushroomType.SOUTH_EAST);
                }
            }
        }
        return ShelfMushroomType.NORTH_SINGLE;
    }
}


