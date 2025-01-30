package net.disjoint.uncancelledbirchforests;

import net.disjoint.uncancelledbirchforests.world.feature.tree.UBFSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static net.minecraft.block.Blocks.*;

public class UBFBlocks {

    //hollow logs

    public static final Block HOLLOW_OAK_LOG = registerBlock("hollow_oak_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(OAK_LOG).nonOpaque());
    public static final Block STRIPPED_HOLLOW_OAK_LOG = registerBlock("stripped_hollow_oak_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_OAK_LOG).nonOpaque());
    public static final Block HOLLOW_BIRCH_LOG = registerBlock("hollow_birch_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(BIRCH_LOG).nonOpaque());
    public static final Block STRIPPED_HOLLOW_BIRCH_LOG = registerBlock("stripped_hollow_birch_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_BIRCH_LOG).nonOpaque());
    public static final Block HOLLOW_SPRUCE_LOG = registerBlock("hollow_spruce_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(SPRUCE_LOG).nonOpaque());
    public static final Block STRIPPED_HOLLOW_SPRUCE_LOG = registerBlock("stripped_hollow_spruce_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_SPRUCE_LOG).nonOpaque());
    public static final Block HOLLOW_JUNGLE_LOG = registerBlock("hollow_jungle_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(JUNGLE_LOG).nonOpaque());
    public static final Block STRIPPED_HOLLOW_JUNGLE_LOG = registerBlock("stripped_hollow_jungle_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_JUNGLE_LOG).nonOpaque());
    public static final Block HOLLOW_ACACIA_LOG = registerBlock("hollow_acacia_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(ACACIA_LOG).nonOpaque());
    public static final Block STRIPPED_HOLLOW_ACACIA_LOG = registerBlock("stripped_hollow_acacia_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_ACACIA_LOG).nonOpaque());
    public static final Block HOLLOW_DARK_OAK_LOG = registerBlock("hollow_dark_oak_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(DARK_OAK_LOG).nonOpaque());
    public static final Block STRIPPED_HOLLOW_DARK_OAK_LOG = registerBlock("stripped_hollow_dark_oak_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_DARK_OAK_LOG).nonOpaque());
    public static final Block HOLLOW_CRIMSON_STEM = registerBlock("hollow_crimson_stem",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(CRIMSON_STEM).nonOpaque());
    public static final Block STRIPPED_HOLLOW_CRIMSON_STEM = registerBlock("stripped_hollow_crimson_stem",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_CRIMSON_STEM).nonOpaque());
    public static final Block HOLLOW_WARPED_STEM = registerBlock("hollow_warped_stem",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(WARPED_STEM).nonOpaque());
    public static final Block STRIPPED_HOLLOW_WARPED_STEM = registerBlock("stripped_hollow_warped_stem",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_WARPED_STEM).nonOpaque());
    public static final Block HOLLOW_MANGROVE_LOG = registerBlock("hollow_mangrove_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(MANGROVE_LOG).nonOpaque());
    public static final Block STRIPPED_HOLLOW_MANGROVE_LOG = registerBlock("stripped_hollow_mangrove_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_MANGROVE_LOG).nonOpaque());
    public static final Block HOLLOW_CHERRY_LOG = registerBlock("hollow_cherry_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(CHERRY_LOG).nonOpaque());
    public static final Block STRIPPED_HOLLOW_CHERRY_LOG = registerBlock("stripped_hollow_cherry_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_CHERRY_LOG).nonOpaque());
    public static final Block HOLLOW_PALE_OAK_LOG = registerBlock("hollow_pale_oak_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(PALE_OAK_LOG).nonOpaque());
    public static final Block STRIPPED_HOLLOW_PALE_OAK_LOG = registerBlock("stripped_hollow_pale_oak_log",
            HollowPillarBlock::new, AbstractBlock.Settings.copy(STRIPPED_PALE_OAK_LOG).nonOpaque());
    public static final Block TESTER_SAPLING = registerSaplingBlock("tester_sapling", UBFSaplingGenerators.TESTER, OAK_SAPLING);

    //mushies

    public static final Block SHELF_MUSHROOM = registerBlock("shelf_mushroom",
            ShelfMushroomBlock::new, AbstractBlock.Settings.copy(BROWN_MUSHROOM));

    //flowers

    public static final Block STARFLOWER = registerBlock("starflower",
            TallFlowerBlock::new, AbstractBlock.Settings.copy(LILAC));


    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return registerBlock(name, true, factory, settings);
    }
    private static Block registerBlock(String name, boolean createItem, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = Blocks.register(keyOf(name), factory, settings);
        if (createItem) { registerBlockItem(name, block); }
        return block;
    }
    private static RegistryKey<Block> keyOf(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(UncancelledBirchForests.MOD_ID, name));
    }

    private static void registerBlockItem(String name, Block block) {
        Identifier id = Identifier.of(UncancelledBirchForests.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Registry.register(Registries.ITEM, key, new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key)));
    }

    private static Block registerSaplingBlock(String name, SaplingGenerator generator, Block base) {
        return registerBlock(name, settings -> new SaplingBlock(generator, settings), AbstractBlock.Settings.copy(base));
    }

    public static void registerModBlocks() {
        UncancelledBirchForests.LOGGER.info("Registering blocks for " + UncancelledBirchForests.MOD_ID);
    }
}
