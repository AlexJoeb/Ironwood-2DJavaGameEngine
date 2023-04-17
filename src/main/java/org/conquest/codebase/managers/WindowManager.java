package org.conquest.codebase.managers;

import javax.swing.*;
import java.awt.*;

public class WindowManager {
    public static final String WINDOW_TITLE = "Conquest";
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    private Canvas canvas;

    private WindowManager() {
        createWindow();
        getCanvas().addKeyListener(InputManager.getInstance());
        getCanvas().addMouseListener(InputManager.getInstance());
        getCanvas().addMouseMotionListener(InputManager.getInstance());
    }

    private static class SingletonHolder {
        private static final WindowManager INSTANCE = new WindowManager();
    }

    public static WindowManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private void createWindow() {
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
