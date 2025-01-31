package net.disjoint.uncancelledbirchforests.world.biome.surface;

import net.disjoint.uncancelledbirchforests.world.biome.UBFBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class UBFMaterialRules {
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS = makeStateRule(Blocks.GRASS_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        MaterialRules.MaterialCondition isAboveSeaLevel = MaterialRules.aboveY(YOffset.fixed(59), 0);
        MaterialRules.MaterialCondition isInBirchForest = MaterialRules.biome(UBFBiomes.BIRCH_FOREST);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS), DIRT);
        MaterialRules.MaterialRule grassSurface1 = MaterialRules.sequence(MaterialRules.condition(isAboveSeaLevel, grassSurface));
        MaterialRules.MaterialRule grassSurface2 = MaterialRules.sequence(MaterialRules.condition(isInBirchForest, grassSurface1));

        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface2)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}