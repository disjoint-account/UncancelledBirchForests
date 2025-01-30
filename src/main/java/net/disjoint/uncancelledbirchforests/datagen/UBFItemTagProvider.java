package net.disjoint.uncancelledbirchforests.datagen;

import net.disjoint.uncancelledbirchforests.UBFBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class UBFItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public UBFItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.OAK_LOGS)
                .add(UBFBlocks.HOLLOW_OAK_LOG.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_OAK_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.BIRCH_LOGS)
                .add(UBFBlocks.HOLLOW_BIRCH_LOG.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_BIRCH_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.SPRUCE_LOGS)
                .add(UBFBlocks.HOLLOW_SPRUCE_LOG.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_SPRUCE_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.JUNGLE_LOGS)
                .add(UBFBlocks.HOLLOW_JUNGLE_LOG.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_JUNGLE_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.ACACIA_LOGS)
                .add(UBFBlocks.HOLLOW_ACACIA_LOG.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_ACACIA_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.DARK_OAK_LOGS)
                .add(UBFBlocks.HOLLOW_DARK_OAK_LOG.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.CRIMSON_STEMS)
                .add(UBFBlocks.HOLLOW_CRIMSON_STEM.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_CRIMSON_STEM.asItem());

        getOrCreateTagBuilder(ItemTags.WARPED_STEMS)
                .add(UBFBlocks.HOLLOW_WARPED_STEM.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_WARPED_STEM.asItem());

        getOrCreateTagBuilder(ItemTags.MANGROVE_LOGS)
                .add(UBFBlocks.HOLLOW_MANGROVE_LOG.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_MANGROVE_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.CHERRY_LOGS)
                .add(UBFBlocks.HOLLOW_CHERRY_LOG.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_CHERRY_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.PALE_OAK_LOGS)
                .add(UBFBlocks.HOLLOW_CHERRY_LOG.asItem())
                .add(UBFBlocks.STRIPPED_HOLLOW_CHERRY_LOG.asItem());
    }
}