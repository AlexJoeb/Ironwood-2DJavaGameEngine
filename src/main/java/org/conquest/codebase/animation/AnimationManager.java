package org.conquest.codebase.animation;

import org.conquest.codebase.objects.sprites.Sprite;
import org.javatuples.Triplet;

import java.util.HashMap;

public class AnimationManager {
    private AnimationManager() {
        animations = new HashMap<>();
    }

    private static class SingletonHolder {
        private static final AnimationManager INSTANCE = new AnimationManager();
    }

    public static AnimationManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private final HashMap<Triplet<Sprite, AnimationType, Direction>, Animation> animations;

    public void loadAllAnimations() {
        animations.clear();
        for(Sprite sprite : Sprite.values()) {
            for(AnimationType animationType : AnimationType.values()) {
                for(Direction direction : Direction.values()) {
                    AnimationTypeConstants animationInfo = AnimationTypeConstants.valueOf(animationType.name() + "_" + direction.name());
                    animations.put(new Triplet<>(sprite, animationType, direction), new Animation(
                            sprite,
                            animationType,
                            direction,
                            animationInfo.getRow(),
                            animationInfo.getFrameCount(),
                            animationInfo.getKeyCode(),
                            animationInfo.mustFinish()
                    ));
                }
            }
        }
    }

    public HashMap<Triplet<Sprite, AnimationType, Direction>, Animation> getAnimations() {
        return animations;
    }

    public Animation getAnimation(Sprite sprite, AnimationType type, Direction direction) {
        return animations.get(new Triplet<>(sprite, type, direction));
    }
}
