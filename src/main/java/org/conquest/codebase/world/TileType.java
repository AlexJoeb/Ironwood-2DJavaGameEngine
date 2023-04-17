package org.conquest.codebase.world;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public enum TileType {
    GRASS("path"),
    SAND("path"),
    DIRT("path"),
    STONE("path");

    private String path;
    private BufferedImage image;

    TileType(String path) {
        this.path = path;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getPath() {
        return path;
    }
}
