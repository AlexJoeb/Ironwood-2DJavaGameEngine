package org.conquest.codebase.world;

import org.javatuples.Pair;

public class Tile {
    public static final int TILE_SIZE = 32;

    private Coordinates coordinates;
    private TileType type;

    public Tile(Coordinates coordinates, TileType type) {
        this.coordinates = coordinates;
        this.type = type;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public TileType getType() {
        return type;
    }
}
