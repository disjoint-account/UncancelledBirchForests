package net.disjoint.uncancelledbirchforests.world.feature.tree;

import net.disjoint.uncancelledbirchforests.UncancelledBirchForests;
import net.disjoint.uncancelledbirchforests.mixin.MixinTrunkPlacerTypeInvoker;
import net.disjoint.uncancelledbirchforests.world.feature.tree.custom.BirchTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class UBFTrunkPlacerTypes {
    public static final TrunkPlacerType<?> BIRCH_TRUNK_PLACER = MixinTrunkPlacerTypeInvoker.callRegister("birch_trunk_placer",
            BirchTrunkPlacer.CODEC);

    public static void register() {
        UncancelledBirchForests.LOGGER.info("Registering Trunk Placers for " + UncancelledBirchForests.MOD_ID);
    }
}
