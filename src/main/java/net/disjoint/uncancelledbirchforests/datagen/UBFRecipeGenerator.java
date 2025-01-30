package net.disjoint.uncancelledbirchforests.datagen;

import net.disjoint.uncancelledbirchforests.UBFBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class UBFRecipeGenerator extends FabricRecipeProvider {
    public UBFRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                offerHollowLogRecipe(UBFBlocks.HOLLOW_OAK_LOG, Blocks.OAK_LOG);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_BIRCH_LOG, Blocks.BIRCH_LOG);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_SPRUCE_LOG, Blocks.SPRUCE_LOG);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_JUNGLE_LOG, Blocks.JUNGLE_LOG);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_ACACIA_LOG, Blocks.ACACIA_LOG);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_DARK_OAK_LOG, Blocks.DARK_OAK_LOG);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_CRIMSON_STEM, Blocks.CRIMSON_STEM);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_WARPED_STEM, Blocks.WARPED_STEM);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_MANGROVE_LOG, Blocks.MANGROVE_LOG);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_CHERRY_LOG, Blocks.CHERRY_LOG);
                offerHollowLogRecipe(UBFBlocks.HOLLOW_PALE_OAK_LOG, Blocks.PALE_OAK_LOG);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_OAK_LOG,Blocks.STRIPPED_OAK_LOG);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_BIRCH_LOG,Blocks.STRIPPED_BIRCH_LOG);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_SPRUCE_LOG,Blocks.STRIPPED_SPRUCE_LOG);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_JUNGLE_LOG,Blocks.STRIPPED_JUNGLE_LOG);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_ACACIA_LOG,Blocks.STRIPPED_ACACIA_LOG);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG,Blocks.STRIPPED_DARK_OAK_LOG);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_CRIMSON_STEM,Blocks.STRIPPED_CRIMSON_STEM);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_WARPED_STEM,Blocks.STRIPPED_WARPED_STEM);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_MANGROVE_LOG,Blocks.STRIPPED_MANGROVE_LOG);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_CHERRY_LOG,Blocks.STRIPPED_CHERRY_LOG);
                offerHollowLogRecipe(UBFBlocks.STRIPPED_HOLLOW_PALE_OAK_LOG,Blocks.STRIPPED_PALE_OAK_LOG);
            }

            private void offerHollowLogRecipe(ItemConvertible output, ItemConvertible input) {
                createShaped(RecipeCategory.BUILDING_BLOCKS, output, 8)
                        .group("hollow_log")
                        .input('#', input)
                        .pattern("###")
                        .pattern("# #")
                        .pattern("###")
                        .criterion(hasItem(input), conditionsFromItem(input))
                        .offerTo(exporter);
            }
        };
    }
    @Override
    public String getName() {
        return "Uncancelled Birch Forests Recipes";
    }
}
