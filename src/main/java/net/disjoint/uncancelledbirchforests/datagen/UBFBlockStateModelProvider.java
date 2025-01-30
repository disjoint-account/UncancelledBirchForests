package net.disjoint.uncancelledbirchforests.datagen;

import net.disjoint.uncancelledbirchforests.UBFBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class UBFBlockStateModelProvider extends FabricModelProvider {

    public UBFBlockStateModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(UBFBlocks.SHELF_MUSHROOM.asItem(), Models.GENERATED);
    }
}