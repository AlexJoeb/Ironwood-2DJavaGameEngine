package org.conquest.codebase.world;

import java.util.HashMap;
import java.util.Map;

public class Chunk {

    public static final int CHUNK_SIZE = 16;

    private Coordinates coordinates;
    private Map<Coordinates, Tile> tiles;

    public Chunk(Coordinates coordinates, Map<Coordinates, Tile> tiles) {
        this.coordinates = coordinates;
        this.tiles = tiles;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Tile addTile(Coordinates coordinates, Tile tile) {
        tiles.put(coordinates, tile);
        return tile;
    }

    public Tile addTile(Coordinates coordinates, TileType type) {
        return addTile(coordinates, new Tile(coordinates, type));
    }

    public Tile addTile(double x, double y, Tile tile) {
        tiles.put(new Coordinates(x, y), tile);
        return tile;
    }

    public Tile addTile(double x, double y, TileType type) {
        return addTile(x, y, new Tile(new Coordinates(x, y), type));
    }

    public Map<Coordinates, Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y) {
        for(Map.Entry<Coordinates, Tile> entry : tiles.entrySet()) {
            if(entry.getKey().getX() == x && entry.getKey().getY() == y) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void setTile(int x, int y, Tile tile) {
        for(Map.Entry<Coordinates, Tile> entry : tiles.entrySet()) {
            if(entry.getKey().getX() == x && entry.getKey().getY() == y) {
                entry.setValue(tile);
            }
        }
    }
}
