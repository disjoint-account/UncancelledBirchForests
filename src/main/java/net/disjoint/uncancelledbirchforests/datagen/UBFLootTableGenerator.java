package net.disjoint.uncancelledbirchforests.datagen;

import net.disjoint.uncancelledbirchforests.UBFBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class UBFLootTableGenerator extends FabricBlockLootTableProvider {
    public UBFLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        addDrop(UBFBlocks.HOLLOW_OAK_LOG);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_OAK_LOG);
        addDrop(UBFBlocks.HOLLOW_BIRCH_LOG);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_BIRCH_LOG);
        addDrop(UBFBlocks.HOLLOW_SPRUCE_LOG);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_SPRUCE_LOG);
        addDrop(UBFBlocks.HOLLOW_JUNGLE_LOG);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_JUNGLE_LOG);
        addDrop(UBFBlocks.HOLLOW_ACACIA_LOG);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_ACACIA_LOG);
        addDrop(UBFBlocks.HOLLOW_DARK_OAK_LOG);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG);
        addDrop(UBFBlocks.HOLLOW_CRIMSON_STEM);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_CRIMSON_STEM);
        addDrop(UBFBlocks.HOLLOW_WARPED_STEM);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_WARPED_STEM);
        addDrop(UBFBlocks.HOLLOW_MANGROVE_LOG);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_MANGROVE_LOG);
        addDrop(UBFBlocks.HOLLOW_CHERRY_LOG);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_CHERRY_LOG);
        addDrop(UBFBlocks.HOLLOW_PALE_OAK_LOG);
        addDrop(UBFBlocks.STRIPPED_HOLLOW_PALE_OAK_LOG);
        addDrop(UBFBlocks.SHELF_MUSHROOM);

    }
}
