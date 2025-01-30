package net.disjoint.uncancelledbirchforests.datagen;

import net.disjoint.uncancelledbirchforests.world.feature.UBFConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class UBFDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(UBFWorldGenerator::new);
		pack.addProvider(UBFBlockStateModelProvider::new);
		pack.addProvider(UBFRecipeGenerator::new);
		pack.addProvider(UBFBlockTagProvider::new);
		pack.addProvider(UBFItemTagProvider::new);
		pack.addProvider(UBFLootTableGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, UBFConfiguredFeatures::bootstrap);
	}
}
