package net.disjoint.uncancelledbirchforests.world.feature.tree;

import net.disjoint.uncancelledbirchforests.UncancelledBirchForests;
import net.disjoint.uncancelledbirchforests.world.feature.UBFConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class UBFSaplingGenerators {
    public static final SaplingGenerator TESTER = new SaplingGenerator(UncancelledBirchForests.MOD_ID + ":tester",
            Optional.empty(), Optional.of(UBFConfiguredFeatures.UBF_BIRCH), Optional.empty());
}
