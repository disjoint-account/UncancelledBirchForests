package net.disjoint.uncancelledbirchforests.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class UBFDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(UBFBlockStateModelProvider::new);
		pack.addProvider(UBFRecipeGenerator::new);
		pack.addProvider(UBFBlockTagProvider::new);
		pack.addProvider(UBFItemTagProvider::new);
		pack.addProvider(UBFLootTableGenerator::new);
	}
}
