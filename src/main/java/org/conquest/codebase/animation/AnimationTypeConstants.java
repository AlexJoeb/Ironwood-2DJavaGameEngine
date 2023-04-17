package org.conquest.codebase.animation;

import java.awt.event.KeyEvent;

public enum AnimationTypeConstants {
    WALKING_S(0, 8, KeyEvent.VK_S, false),
    WALKING_N(1, 8, KeyEvent.VK_W, false),
    WALKING_E(2, 8, KeyEvent.VK_D, false),
    WALKING_W(3, 8, KeyEvent.VK_A, false),
    JUMPING_S(4, 5, KeyEvent.VK_SPACE, true),
    JUMPING_N(5, 5, KeyEvent.VK_SPACE, true),
    JUMPING_E(6, 5, KeyEvent.VK_SPACE, true),
    JUMPING_W(7, 5, KeyEvent.VK_SPACE, true);

    private final int row;
    private final int frameCount;
    private final int keyCode;
    private final boolean mustFinish;

    AnimationTypeConstants(int row, int frameCount, int keyCode, boolean mustFinish) {
        this.row = row;
        this.frameCount = frameCount;
        this.keyCode = keyCode;
        this.mustFinish = mustFinish;
    }

    public int getRow() {
        return row;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean mustFinish() {
        return mustFinish;
    }
}
