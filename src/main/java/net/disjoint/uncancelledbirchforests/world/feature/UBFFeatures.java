package net.disjoint.uncancelledbirchforests.world.feature;

import net.disjoint.uncancelledbirchforests.UncancelledBirchForests;
import net.disjoint.uncancelledbirchforests.world.feature.features.FallenLogFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public abstract class UBFFeatures<FC extends FeatureConfig> {
    public static Feature<DefaultFeatureConfig> FALLEN_LOG_FEATURE;

    public static void register() {
        FALLEN_LOG_FEATURE = registerFeature("fallen_log", new FallenLogFeature<>(DefaultFeatureConfig.CODEC));


        UncancelledBirchForests.LOGGER.info("Registering Features for " + UncancelledBirchForests.MOD_ID);
    }

    private static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
        return Registry.register(Registries.FEATURE, name, feature);
    }
}