package net.disjoint.uncancelledbirchforests;

import net.disjoint.uncancelledbirchforests.world.feature.UBFFeatures;
import net.disjoint.uncancelledbirchforests.world.feature.tree.UBFTreeDecoratorType;
import net.disjoint.uncancelledbirchforests.world.feature.tree.UBFTrunkPlacerTypes;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UncancelledBirchForests implements ModInitializer {
	public static final String MOD_ID = "uncancelledbirchforests";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		UBFItemGroup.registerItemGroups();
		UBFBlocks.registerModBlocks();
		UBFFeatures.register();
		UBFTrunkPlacerTypes.register();
		UBFTreeDecoratorType.register();
		Instance.init();
	}
}