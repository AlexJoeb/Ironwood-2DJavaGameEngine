package org.conquest.codebase.world;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {
    private List<Chunk> chunks;

    private World() {}
    private static class SingletonHolder {
        private static final World INSTANCE = new World();
    }
    public static World getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Chunk addChunk(Coordinates coordinates) {
        return addChunk(coordinates, new HashMap<>());
    }

    public Chunk addChunk(Coordinates coordinates, Map<Coordinates, Tile> tiles) {
        Chunk chunk = new Chunk(coordinates, tiles);
        chunks.add(chunk);
        return chunk;
    }

    public void addChunk(Chunk chunk) {
        chunks.add(chunk);
    }

    public void removeChunk(Chunk chunk) {
        chunks.remove(chunk);
    }

    public Chunk getChunk(int x, int y) {
        for (Chunk chunk : chunks) {
            if (chunk.getCoordinates().getX() == x && chunk.getCoordinates().getY() == y) {
                return chunk;
            }
        }
        return null;
    }

    public void render(Graphics g) {
        // g.fillRect(Player.getInstance().getWorldX() + i * TILE_SIZE, Player.getInstance().getWorldY() + j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
