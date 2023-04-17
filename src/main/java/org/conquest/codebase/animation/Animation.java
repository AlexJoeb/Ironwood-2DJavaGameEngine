package org.conquest.codebase.animation;

import org.conquest.codebase.objects.sprites.Sprite;
import org.conquest.codebase.objects.sprites.SpriteSheets;

import java.awt.image.BufferedImage;

public class Animation {
    private final Sprite sprite;
    private final BufferedImage spriteSheet;
    private final AnimationType type;
    private final Direction direction;
    private final int row;
    private final int totalFrames;
    private final int keyCode;
    private final boolean mustFinishAnimation;
    private BufferedImage[] frames;

    public Animation(final Sprite sprite, final AnimationType type, final Direction direction, final int row, final int totalFrames, final int keyCode) {
        this(sprite, type, direction, row, totalFrames, keyCode, false);
    }

    public Animation(final Sprite sprite, final AnimationType type, final Direction direction, final int row, final int totalFrames, final int keyCode, final boolean mustFinishAnimation) {
        this.sprite = sprite;
        this.spriteSheet = SpriteSheets.getInstance().getSpriteSheet(sprite);
        this.type = type;
        this.direction = direction;
        this.row = row;
        this.totalFrames = totalFrames;
        this.keyCode = keyCode;
        this.mustFinishAnimation = mustFinishAnimation;
        loadFrames();
    }

    private void loadFrames() {
        frames = new BufferedImage[totalFrames];
        for (int i = 0; i < totalFrames; i++) {
            frames[i] = spriteSheet.getSubimage(i * sprite.getWidth(), row * sprite.getHeight(), sprite.getWidth(), sprite.getHeight());
        }
    }

    public int getRow() {
        return row;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public BufferedImage[] getFrames() {
        return frames;
    }

    public BufferedImage getFrame(int index) {
        return frames[index];
    }

    public int getKeyCode() {
        return keyCode;
    }

    public AnimationType getType() {
        return type;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean mustFinishAnimation() {
        return mustFinishAnimation;
    }
}
