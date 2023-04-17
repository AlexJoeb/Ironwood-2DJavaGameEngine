package org.conquest.codebase.objects.sprites;

import org.conquest.codebase.world.Tile;

public enum Sprite {
    CHARACTER_ONE("/sprites/characters/char1.png"),
    CHARACTER_TWO("/sprites/characters/char2.png"),
    CHARACTER_THREE("/sprites/characters/char3.png"),
    CHARACTER_FOUR("/sprites/characters/char4.png"),
    CHARACTER_FIVE("/sprites/characters/char5.png"),
    CHARACTER_SIX("/sprites/characters/char6.png"),
    CHARACTER_SEVEN("/sprites/characters/char7.png"),
    CHARACTER_EIGHT("/sprites/characters/char8.png");

    private final String path;

    Sprite(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public int getWidth() {
        return Tile.TILE_SIZE;
    }

    public int getHeight() {
        return Tile.TILE_SIZE;
    }
}