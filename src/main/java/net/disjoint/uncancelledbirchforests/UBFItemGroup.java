package net.disjoint.uncancelledbirchforests;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class UBFItemGroup {
    public static ItemGroup UNCANCELLEDBIRCHFORESTS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(UncancelledBirchForests.MOD_ID, "uncancelledbirchforests"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.uncancelledbirchforests"))
                    .icon(() -> new ItemStack(UBFBlocks.HOLLOW_BIRCH_LOG)).entries((displayContext, entries) -> {
                            entries.add(UBFBlocks.HOLLOW_OAK_LOG);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_OAK_LOG);
                            entries.add(UBFBlocks.HOLLOW_BIRCH_LOG);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_BIRCH_LOG);
                            entries.add(UBFBlocks.HOLLOW_SPRUCE_LOG);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_SPRUCE_LOG);
                            entries.add(UBFBlocks.HOLLOW_JUNGLE_LOG);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_JUNGLE_LOG);
                            entries.add(UBFBlocks.HOLLOW_ACACIA_LOG);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_ACACIA_LOG);
                            entries.add(UBFBlocks.HOLLOW_DARK_OAK_LOG);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG);
                            entries.add(UBFBlocks.HOLLOW_CRIMSON_STEM);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_CRIMSON_STEM);
                            entries.add(UBFBlocks.HOLLOW_WARPED_STEM);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_WARPED_STEM);
                            entries.add(UBFBlocks.HOLLOW_MANGROVE_LOG);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_MANGROVE_LOG);
                            entries.add(UBFBlocks.HOLLOW_CHERRY_LOG);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_CHERRY_LOG);
                            entries.add(UBFBlocks.HOLLOW_PALE_OAK_LOG);
                            entries.add(UBFBlocks.STRIPPED_HOLLOW_PALE_OAK_LOG);
                            entries.add(UBFBlocks.SHELF_MUSHROOM);
}).build());
    public static void registerItemGroups() {
        UncancelledBirchForests.LOGGER.info("Registering Item Groups for " + UncancelledBirchForests.MOD_ID);
    }
}