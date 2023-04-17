package org.conquest.codebase.objects.sprites;

import org.conquest.codebase.world.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SpriteSheets {
    // Create a lazy-loaded singleton instance of the SpriteSheetManager.
    private SpriteSheets() {}
    private static class SingletonHelper {
        private static final SpriteSheets INSTANCE = new SpriteSheets();
    }
    public static SpriteSheets getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Define the sprite sheet map.
    private final Map<Sprite, BufferedImage> spriteSheets = new HashMap<>();

    // Load the sprite sheets.
    public void loadSpriteSheet(Sprite sprite) {
        try {
            InputStream ios = getClass().getResourceAsStream(sprite.getPath());
            if(ios == null) {
                throw new IOException("Could not find sprite sheet: " + sprite.getPath());
            }
            BufferedImage spriteSheet = ImageIO.read(ios);
            spriteSheets.put(sprite, spriteSheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAllSpriteSheets() {
        for (Sprite sprite : Sprite.values()) {
            loadSpriteSheet(sprite);
        }
    }

    public BufferedImage getSpriteSheet(Sprite sprite) {
        return spriteSheets.get(sprite);
    }

    public BufferedImage getSprite(Sprite sprite, int x, int y) {
        return spriteSheets.get(sprite).getSubimage(x * Tile.TILE_SIZE, y, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }
}