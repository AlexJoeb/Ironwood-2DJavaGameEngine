package org.conquest.codebase.objects.player;

import org.conquest.codebase.animation.Animation;
import org.conquest.codebase.animation.AnimationManager;
import org.conquest.codebase.animation.AnimationType;
import org.conquest.codebase.animation.Direction;
import org.conquest.codebase.managers.WindowManager;
import org.conquest.codebase.objects.sprites.Sprite;
import org.conquest.codebase.world.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final int MOVE_SPEED = 2;

    private int worldX = 0;
    private int worldY = 0;

    // Animation
    private Sprite sprite;
    private List<Animation> animations = new ArrayList<>();
    private Direction lastDirectionFaced = Direction.S;
    private int animationFrame = 0;

    private Player(Sprite sprite) {
        this.sprite = sprite;
    }

    public static void getInstance(Sprite sprite) {
        if (SingletonHolder.INSTANCE == null) {
            SingletonHolder.INSTANCE = new Player(sprite);
        }
    }

    public static Player getInstance() {
        if (SingletonHolder.INSTANCE == null) {
            throw new IllegalStateException("Player has not been initialized yet");
        }
        return SingletonHolder.INSTANCE;
    }

    // Sprite
    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    // Animation
    public List<Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(List<Animation> animations) {
        this.animations = animations;
    }

    public List<Animation> getWalkingAnimations() {
        return animations.stream().filter(a -> a.getType() == AnimationType.WALKING).toList();
    }

    public List<Animation> getNonWalkingAnimations() {
        return animations.stream().filter(a -> a.getType() != AnimationType.WALKING).toList();
    }

    public int countAnimations() {
        return animations.size();
    }

    public void addAnimation(Animation animation) {
        if (animation.getType() == AnimationType.WALKING) {
            if (getWalkingAnimations().size() < 2) {
                animations.add(animation);
                lastDirectionFaced = animation.getDirection();
            }
        } else {
            if (getNonWalkingAnimations().isEmpty()) {
                animations.add(animation);
                lastDirectionFaced = animation.getDirection();
            }
        }
    }

    public void removeAnimation(Animation animation) {
        if (animations.contains(animation)) {
            if (animation.getType() == AnimationType.WALKING) {
                List<Animation> walkingAnimations = getWalkingAnimations();
                if (!walkingAnimations.isEmpty()) {
                    lastDirectionFaced = walkingAnimations.get(0).getDirection();
                }
            }
            animations.remove(animation);
        }
    }

    public Direction getFacing() {
        Animation currentAnimation = getCurrentAnimation();
        if (currentAnimation != null) {
            return currentAnimation.getDirection();
        } else {
            return lastDirectionFaced;
        }
    }

    public void processJump() {
        if (isJumping()) return;
        Animation jumpAnimation = AnimationManager.getInstance().getAnimation(sprite, AnimationType.JUMPING, getFacing());
        addAnimation(jumpAnimation);
        this.animationFrame = 0;
    }

    public boolean isJumping() {
        return getNonWalkingAnimations().stream().anyMatch(a -> a.getType() == AnimationType.JUMPING);
    }

    public boolean isAnimating() {
        return !animations.isEmpty();
    }

    public Animation getCurrentAnimation() {
        if (!isAnimating()) {
            return AnimationManager.getInstance().getAnimation(sprite, AnimationType.WALKING, lastDirectionFaced);
        } else {
            List<Animation> nonWalkingAnimations = getNonWalkingAnimations();
            if (!nonWalkingAnimations.isEmpty()) {
                return nonWalkingAnimations.get(0);
            } else {
                List<Animation> walkingAnimations = getWalkingAnimations();
                return walkingAnimations.get(walkingAnimations.size() - 1);
            }
        }
    }

    public BufferedImage getCurrentAnimationFrame() {
        return getCurrentAnimation().getFrames()[animationFrame];
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void move(Direction direction, double deltaTime) {
        double multiplier = -((MOVE_SPEED / 30.0) * deltaTime);
        switch (direction) {
            case S -> worldY += multiplier;
            case N -> worldY -= multiplier;
            case E -> worldX += multiplier;
            case W -> worldX -= multiplier;
        }
    }

    public void updateAnimation(double currentTime, double deltaTime) {
        if (!isAnimating()) {
            animationFrame = 0;
            return;
        }

        List<Animation> walkingAnimations = getWalkingAnimations();
        if (!walkingAnimations.isEmpty()) {
            walkingAnimations.forEach(a -> {
                move(a.getDirection(), deltaTime);
            });
        }

        if (deltaTime >= MOVE_SPEED) {
            animationFrame++;
            if (animationFrame >= getCurrentAnimation().getFrames().length) {
                if (getCurrentAnimation().getType() != AnimationType.WALKING) {
                    removeAnimation(getCurrentAnimation());
                }
                animationFrame = 0;
            }
        }
    }

    private static class SingletonHolder {
        private static Player INSTANCE;
    }

    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (WindowManager.WINDOW_WIDTH / 2) - Tile.TILE_SIZE, (WindowManager.WINDOW_HEIGHT / 2) - Tile.TILE_SIZE, null);
    }
}
