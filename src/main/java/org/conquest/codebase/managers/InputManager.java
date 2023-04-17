package org.conquest.codebase.managers;

import org.conquest.codebase.animation.Animation;
import org.conquest.codebase.animation.AnimationManager;
import org.conquest.codebase.animation.AnimationType;
import org.conquest.codebase.animation.Direction;
import org.conquest.codebase.objects.player.Player;

import java.awt.event.*;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener {

    private InputManager() {
    }

    public static InputManager getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Player player = Player.getInstance();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_D -> {
                Animation animation = AnimationManager.getInstance().getAnimation(player.getSprite(), AnimationType.WALKING, Direction.getByKeyEvent(e));
                if(!player.getAnimations().contains(animation))
                    player.addAnimation(animation);
            }
            case KeyEvent.VK_SPACE -> player.processJump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Player player = Player.getInstance();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_D -> {
                Animation animation = AnimationManager.getInstance().getAnimation(player.getSprite(), AnimationType.WALKING, Direction.getByKeyEvent(e));
                player.removeAnimation(animation);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private static class SingletonHelper {
        private static final InputManager INSTANCE = new InputManager();
    }
}
