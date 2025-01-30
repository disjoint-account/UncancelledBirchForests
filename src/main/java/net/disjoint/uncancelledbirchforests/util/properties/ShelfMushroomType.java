package net.disjoint.uncancelledbirchforests.util.properties;

import net.minecraft.util.StringIdentifiable;

public enum ShelfMushroomType implements StringIdentifiable {
    NORTH_SINGLE("north_single"),
    NORTH_STRAIGHT("north_straight"),
    EAST_SINGLE("east_single"),
    EAST_STRAIGHT("east_straight"),
    SOUTH_SINGLE("south_single"),
    SOUTH_STRAIGHT("south_straight"),
    WEST_SINGLE("west_single"),
    WEST_STRAIGHT("west_straight"),
    NORTH_EAST("north_east"),
    SOUTH_EAST("south_east"),
    NORTH_WEST("north_west"),
    SOUTH_WEST("south_west");

    private final String name;

    ShelfMushroomType(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public String asString() {
        return this.name;
    }

    public boolean hasNorth() {
        return this == NORTH_SINGLE || this == NORTH_STRAIGHT || this == NORTH_EAST || this == NORTH_WEST;
    }

    public boolean hasEast() {
        return this == EAST_SINGLE || this == EAST_STRAIGHT || this == NORTH_EAST || this == SOUTH_EAST;
    }

    public boolean hasSouth() {
        return this == SOUTH_SINGLE || this == SOUTH_STRAIGHT || this == SOUTH_EAST || this == SOUTH_WEST;
    }

    public boolean hasWest() {
        return this == WEST_SINGLE || this == WEST_STRAIGHT || this == NORTH_WEST || this == SOUTH_WEST;
    }

    public boolean northOnly() {
        return this == NORTH_SINGLE || this == NORTH_STRAIGHT;
    }

    public boolean eastOnly() {
        return this == EAST_SINGLE || this == EAST_STRAIGHT;
    }

    public boolean southOnly() {
        return this == SOUTH_SINGLE || this == SOUTH_STRAIGHT;
    }

    public boolean westOnly() {
        return this == WEST_SINGLE || this == WEST_STRAIGHT;
    }

    public boolean isSingle() {
        return northOnly() || eastOnly() || southOnly() || westOnly();
    }
}