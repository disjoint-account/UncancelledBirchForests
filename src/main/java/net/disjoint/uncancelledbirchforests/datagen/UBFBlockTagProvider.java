package net.disjoint.uncancelledbirchforests.datagen;

import net.disjoint.uncancelledbirchforests.UBFBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class UBFBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public UBFBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(UBFBlocks.HOLLOW_OAK_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_OAK_LOG,
                        UBFBlocks.HOLLOW_BIRCH_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_BIRCH_LOG,
                        UBFBlocks.HOLLOW_SPRUCE_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_SPRUCE_LOG,
                        UBFBlocks.HOLLOW_JUNGLE_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_JUNGLE_LOG,
                        UBFBlocks.HOLLOW_ACACIA_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_ACACIA_LOG,
                        UBFBlocks.HOLLOW_DARK_OAK_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG,
                        UBFBlocks.HOLLOW_CRIMSON_STEM,
                        UBFBlocks.STRIPPED_HOLLOW_CRIMSON_STEM,
                        UBFBlocks.HOLLOW_WARPED_STEM,
                        UBFBlocks.STRIPPED_HOLLOW_WARPED_STEM,
                        UBFBlocks.HOLLOW_MANGROVE_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_MANGROVE_LOG,
                        UBFBlocks.HOLLOW_CHERRY_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_CHERRY_LOG,
                        UBFBlocks.HOLLOW_PALE_OAK_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_PALE_OAK_LOG);

        getOrCreateTagBuilder(BlockTags.OAK_LOGS)
                .add(UBFBlocks.HOLLOW_OAK_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_OAK_LOG);

        getOrCreateTagBuilder(BlockTags.BIRCH_LOGS)
                .add(UBFBlocks.HOLLOW_BIRCH_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_BIRCH_LOG);

        getOrCreateTagBuilder(BlockTags.SPRUCE_LOGS)
                .add(UBFBlocks.HOLLOW_SPRUCE_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_SPRUCE_LOG);

        getOrCreateTagBuilder(BlockTags.JUNGLE_LOGS)
                .add(UBFBlocks.HOLLOW_JUNGLE_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_JUNGLE_LOG);

        getOrCreateTagBuilder(BlockTags.ACACIA_LOGS)
                .add(UBFBlocks.HOLLOW_ACACIA_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_ACACIA_LOG);

        getOrCreateTagBuilder(BlockTags.DARK_OAK_LOGS)
                .add(UBFBlocks.HOLLOW_DARK_OAK_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG);

        getOrCreateTagBuilder(BlockTags.CRIMSON_STEMS)
                .add(UBFBlocks.HOLLOW_CRIMSON_STEM,
                        UBFBlocks.STRIPPED_HOLLOW_CRIMSON_STEM);

        getOrCreateTagBuilder(BlockTags.WARPED_STEMS)
                .add(UBFBlocks.HOLLOW_WARPED_STEM,
                        UBFBlocks.STRIPPED_HOLLOW_WARPED_STEM);

        getOrCreateTagBuilder(BlockTags.MANGROVE_LOGS)
                .add(UBFBlocks.HOLLOW_MANGROVE_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_MANGROVE_LOG);

        getOrCreateTagBuilder(BlockTags.CHERRY_LOGS)
                .add(UBFBlocks.HOLLOW_CHERRY_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_CHERRY_LOG);

        getOrCreateTagBuilder(BlockTags.PALE_OAK_LOGS)
                .add(UBFBlocks.HOLLOW_PALE_OAK_LOG,
                        UBFBlocks.STRIPPED_HOLLOW_PALE_OAK_LOG);

        getOrCreateTagBuilder(BlockTags.FLOWERS)
                .add(UBFBlocks.STARFLOWER);

        getOrCreateTagBuilder(ConventionalBlockTags.TALL_FLOWERS)
                .add(UBFBlocks.STARFLOWER);

        getOrCreateTagBuilder(ConventionalBlockTags.FLOWERS)
                .add(UBFBlocks.STARFLOWER);

        getOrCreateTagBuilder(BlockTags.BEE_ATTRACTIVE)
                .add(UBFBlocks.STARFLOWER);
    }
}