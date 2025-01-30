package net.disjoint.uncancelledbirchforests.world.feature.tree;

import com.mojang.serialization.MapCodec;
import net.disjoint.uncancelledbirchforests.UncancelledBirchForests;
import net.disjoint.uncancelledbirchforests.world.feature.tree.decorators.ShelfMushroomTreeDecorator;
import net.disjoint.uncancelledbirchforests.world.feature.tree.decorators.UBFBeehiveTreeDecorator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.treedecorator.*;

public class UBFTreeDecoratorType <P extends TreeDecorator> {
    public static final TreeDecoratorType<UBFBeehiveTreeDecorator> UBF_BEEHIVE = register("ubf_beehive", UBFBeehiveTreeDecorator.CODEC);
    public static final TreeDecoratorType<ShelfMushroomTreeDecorator> SHELF_MUSHROOM = register("shelf_mushroom", ShelfMushroomTreeDecorator.CODEC);
    private final MapCodec<P> codec;

    private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, MapCodec<P> codec) {
        return Registry.register(Registries.TREE_DECORATOR_TYPE, id, new TreeDecoratorType<>(codec));
    }

    public UBFTreeDecoratorType(MapCodec<P> codec) {
        this.codec = codec;
    }

    public MapCodec<P> getCodec() {
        return this.codec;
    }

    public static void register() {
        UncancelledBirchForests.LOGGER.info("Registering Trunk Placers for " + UncancelledBirchForests.MOD_ID);
    }
}
