package net.disjoint.uncancelledbirchforests.world.feature;

import net.disjoint.uncancelledbirchforests.UncancelledBirchForests;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class UBFPlacedFeatures {
    public static final RegistryKey<PlacedFeature> UBF_BIRCH_PLACED_SMALL = registerKey("ubf_birch_placed_small");
    public static final RegistryKey<PlacedFeature> UBF_BIRCH_PLACED_LARGE = registerKey("ubf_birch_placed_large");
    public static final RegistryKey<PlacedFeature> FALLEN_LOG_PLACED = registerKey("fallen_log_placed");
    public static final RegistryKey<PlacedFeature> STARFLOWER_PLACED = registerKey("starflower_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, UBF_BIRCH_PLACED_SMALL, configuredFeatureRegistryEntryLookup.getOrThrow(UBFConfiguredFeatures.UBF_BIRCH),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(2, 0.5f, 4), Blocks.BIRCH_SAPLING));

        register(context, UBF_BIRCH_PLACED_LARGE, configuredFeatureRegistryEntryLookup.getOrThrow(UBFConfiguredFeatures.UBF_BIRCH),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(4, 0.5f, 8), Blocks.BIRCH_SAPLING));

        register(context, FALLEN_LOG_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(UBFConfiguredFeatures.FALLEN_LOG));

        register(context, STARFLOWER_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(UBFConfiguredFeatures.STARFLOWER));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(UncancelledBirchForests.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
