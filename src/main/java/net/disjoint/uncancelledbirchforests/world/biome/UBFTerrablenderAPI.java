package net.disjoint.uncancelledbirchforests.world.biome;

import net.disjoint.uncancelledbirchforests.UncancelledBirchForests;
import net.disjoint.uncancelledbirchforests.world.biome.surface.UBFMaterialRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class UBFTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new UBFOverworldRegion(Identifier.of(UncancelledBirchForests.MOD_ID, "overworld"), 10));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, UncancelledBirchForests.MOD_ID, UBFMaterialRules.makeRules());
    }
}