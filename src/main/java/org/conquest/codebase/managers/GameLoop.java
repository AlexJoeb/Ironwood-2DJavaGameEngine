package org.conquest.codebase.managers;

import org.conquest.codebase.objects.player.Player;
import org.conquest.codebase.world.Tile;
import org.conquest.codebase.world.World;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameLoop implements Runnable {
    private static double FPS = 60.0;

    private boolean running = false;
    private Thread thread;

    private GameLoop() {}

    private static class SingletonHolder {
        private static GameLoop INSTANCE;
    }

    public static GameLoop getInstance() {
        if (SingletonHolder.INSTANCE == null) {
            SingletonHolder.INSTANCE = new GameLoop();
        }
        return SingletonHolder.INSTANCE;
    }

    public void start() {
        if (running) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        if (!running) return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (running) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update(currentTime, delta);
                render(currentTime, delta);
                drawCount++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    private void update(double currentTime, double delta) {
        // Update player animation.
        Player player = Player.getInstance();
        player.updateAnimation(currentTime, delta);
    }

    private void render(double currentTime, double delta) {
        WindowManager windowManager = WindowManager.getInstance();
        BufferStrategy bs = windowManager.getCanvas().getBufferStrategy();
        if (bs == null) {
            windowManager.getCanvas().createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowManager.WINDOW_WIDTH, WindowManager.WINDOW_HEIGHT);

        // Render game objects.
        g.drawImage(Player.getInstance().getCurrentAnimationFrame(), (WindowManager.WINDOW_WIDTH / 2) - Tile.TILE_SIZE, (WindowManager.WINDOW_HEIGHT / 2) - Tile.TILE_SIZE, null);

        // Render world.
        World.getInstance().render(g);

        g.dispose();
        bs.show();
    }
}