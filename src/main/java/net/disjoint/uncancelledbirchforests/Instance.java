package net.disjoint.uncancelledbirchforests;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class Instance {
    public static void  init(){
        {
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.HOLLOW_OAK_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.STRIPPED_HOLLOW_OAK_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.HOLLOW_BIRCH_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.STRIPPED_HOLLOW_BIRCH_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.HOLLOW_SPRUCE_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.STRIPPED_HOLLOW_SPRUCE_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.HOLLOW_JUNGLE_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.STRIPPED_HOLLOW_JUNGLE_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.HOLLOW_ACACIA_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.STRIPPED_HOLLOW_ACACIA_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.HOLLOW_DARK_OAK_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.HOLLOW_MANGROVE_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.STRIPPED_HOLLOW_MANGROVE_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.HOLLOW_CHERRY_LOG, 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add(UBFBlocks.STRIPPED_HOLLOW_CHERRY_LOG, 5, 5);

            CompostingChanceRegistry.INSTANCE.add(UBFBlocks.SHELF_MUSHROOM, 0.65f);
            CompostingChanceRegistry.INSTANCE.add(UBFBlocks.STARFLOWER, 0.65f);
        }
        addStrippables();
    }
        public static void addStrippables() {
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_OAK_LOG, UBFBlocks.STRIPPED_HOLLOW_OAK_LOG);
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_BIRCH_LOG, UBFBlocks.STRIPPED_HOLLOW_BIRCH_LOG);
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_SPRUCE_LOG, UBFBlocks.STRIPPED_HOLLOW_SPRUCE_LOG);
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_JUNGLE_LOG, UBFBlocks.STRIPPED_HOLLOW_JUNGLE_LOG);
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_ACACIA_LOG, UBFBlocks.STRIPPED_HOLLOW_ACACIA_LOG);
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_DARK_OAK_LOG, UBFBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG);
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_CRIMSON_STEM, UBFBlocks.STRIPPED_HOLLOW_CRIMSON_STEM);
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_WARPED_STEM, UBFBlocks.STRIPPED_HOLLOW_WARPED_STEM);
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_MANGROVE_LOG, UBFBlocks.STRIPPED_HOLLOW_MANGROVE_LOG);
        StrippableBlockRegistry.register(UBFBlocks.HOLLOW_CHERRY_LOG, UBFBlocks.STRIPPED_HOLLOW_CHERRY_LOG);

    }
}

