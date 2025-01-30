package net.disjoint.uncancelledbirchforests.world.feature;

import net.disjoint.uncancelledbirchforests.UncancelledBirchForests;
import net.disjoint.uncancelledbirchforests.world.feature.tree.decorators.ShelfMushroomTreeDecorator;
import net.disjoint.uncancelledbirchforests.world.feature.tree.decorators.UBFBeehiveTreeDecorator;
import net.disjoint.uncancelledbirchforests.world.feature.tree.custom.BirchTrunkPlacer;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class UBFConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?,?>> UBF_BIRCH = registerKey("ubf_birch");

    private static TreeFeatureConfig.Builder birch() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.BIRCH_LOG),
                new BirchTrunkPlacer(7, 2, 0),
                BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        ConfiguredFeatures.register(context, UBF_BIRCH, Feature.TREE, birch().decorators(List.of(new UBFBeehiveTreeDecorator(1.0f), new ShelfMushroomTreeDecorator(1.0f))).build());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(UncancelledBirchForests.MOD_ID, name));
    }


    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
