package org.conquest.codebase.animation;

import java.awt.event.KeyEvent;

public enum Direction {
    S,
    N,
    E,
    W;

    public static Direction getByKeyEvent(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                return N;
            }
            case KeyEvent.VK_S -> {
                return S;
            }
            case KeyEvent.VK_A -> {
                return W;
            }
            case KeyEvent.VK_D -> {
                return E;
            }
            default -> {
                return null;
            }
        }
    }
}