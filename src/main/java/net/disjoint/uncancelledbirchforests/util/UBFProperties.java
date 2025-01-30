package net.disjoint.uncancelledbirchforests.util;

import net.disjoint.uncancelledbirchforests.util.properties.ShelfMushroomType;
import net.minecraft.state.property.EnumProperty;

public class UBFProperties {
    public static EnumProperty<ShelfMushroomType> SHELF_MUSHROOM_TYPE;

    public UBFProperties() {
    }

    static {
        SHELF_MUSHROOM_TYPE = EnumProperty.of("shelf_mushroom_type", ShelfMushroomType.class);
    }
}
