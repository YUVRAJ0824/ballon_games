package com.yuvraj.balloonpop.engine;

/**
 * Dedicated game thread for the 60 FPS game loop.
 *
 * Runs on a separate thread to avoid blocking the UI thread.
 * Calls update() and render() on the GameSurface every frame.
 *
 * Target: 60 FPS = ~16ms per frame.
 */
public class GameThread extends Thread {

    private static final int TARGET_FPS = 60;
    private static final long TARGET_FRAME_TIME = 1000 / TARGET_FPS; // ~16ms

    private final GameSurface surface;
    private volatile boolean running;

    public GameThread(GameSurface surface) {
        super("BalloonGameThread");
        this.surface = surface;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();

        while (running) {
            long now = System.currentTimeMillis();
            long deltaMs = now - lastTime;
            lastTime = now;

            // Update game state
            surface.update(deltaMs);

            // Render frame
            surface.render();

            // Frame rate limiter — sleep if we're ahead of schedule
            long frameTime = System.currentTimeMillis() - now;
            long sleepTime = TARGET_FRAME_TIME - frameTime;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    // interrupted, just continue
                }
            }
        }
    }
}
