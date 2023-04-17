package org.conquest.codebase;

import org.conquest.codebase.animation.AnimationManager;
import org.conquest.codebase.managers.GameLoop;
import org.conquest.codebase.objects.player.Player;
import org.conquest.codebase.objects.sprites.Sprite;
import org.conquest.codebase.objects.sprites.SpriteSheets;
import org.conquest.codebase.world.World;

public class Main {
    public static void main(String[] args) {
        // Load the sprites.
        SpriteSheets.getInstance().loadAllSpriteSheets();

        // Load animations;
        AnimationManager.getInstance().loadAllAnimations();

        // Initialize the player.
        Player.getInstance(Sprite.CHARACTER_THREE);

        // Start Game Loop.
        GameLoop.getInstance().start();
    }
}