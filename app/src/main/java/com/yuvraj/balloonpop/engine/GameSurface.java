package com.yuvraj.balloonpop.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.yuvraj.balloonpop.model.Balloon;
import com.yuvraj.balloonpop.model.BalloonType;

import java.util.ArrayList;
import java.util.List;

/**
 * The main game rendering surface.
 *
 * Uses a SurfaceView with a dedicated GameThread for smooth 60 FPS rendering.
 * Manages balloon spawning, movement, drawing, and touch-to-pop detection.
 *
 * Day 1: Basic skeleton with sky background.
 * Day 3+: Full game loop, balloon drawing, touch handling.
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    // ── Game State ──
    private GameThread gameThread;
    private final List<Balloon> balloons = new ArrayList<>();
    private final Object balloonLock = new Object();

    private int score = 0;
    private int lives = 3;
    private int level = 1;
    private boolean gameRunning = false;

    // ── Drawing ──
    private Paint bgPaint;
    private Paint balloonPaint;
    private Paint textPaint;
    private Paint stringPaint;
    private int surfaceWidth, surfaceHeight;

    // ── Callback to update HUD ──
    private HudCallback hudCallback;

    /**
     * Callback interface for the Activity to receive game state updates.
     */
    public interface HudCallback {
        void onScoreChanged(int score);
        void onLivesChanged(int lives);
        void onLevelChanged(int level);
        void onGameOver(int finalScore);
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        setFocusable(true);
        initPaints();
    }

    private void initPaints() {
        bgPaint = new Paint();

        balloonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(36f);
        textPaint.setFakeBoldText(true);

        stringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        stringPaint.setColor(Color.DKGRAY);
        stringPaint.setStrokeWidth(2f);
    }

    public void setHudCallback(HudCallback callback) {
        this.hudCallback = callback;
    }

    // ── Surface Lifecycle ──

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceWidth = getWidth();
        surfaceHeight = getHeight();

        gameRunning = true;
        gameThread = new GameThread(this);
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        surfaceWidth = width;
        surfaceHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        if (gameThread != null) {
            gameThread.setRunning(false);
            while (retry) {
                try {
                    gameThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    // keep trying
                }
            }
        }
    }

    // ── Game Loop Methods (called by GameThread) ──

    /**
     * Update game state. Called every frame by GameThread.
     * @param deltaMs milliseconds since last frame
     */
    public void update(long deltaMs) {
        if (!gameRunning) return;

        float deltaSec = deltaMs / 1000f;

        synchronized (balloonLock) {
            // TODO (Day 5): Update balloon positions (rise upward)
            // TODO (Day 6): Check for escaped balloons (lose lives)
            // TODO (Day 11): Spawn new balloons based on difficulty
        }
    }

    /**
     * Draw the current frame. Called every frame by GameThread.
     */
    public void render() {
        Canvas canvas = null;
        try {
            canvas = getHolder().lockCanvas();
            if (canvas != null) {
                drawFrame(canvas);
            }
        } finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    private void drawFrame(Canvas canvas) {
        // Sky gradient background
        LinearGradient skyGradient = new LinearGradient(
                0, 0, 0, surfaceHeight,
                Color.rgb(79, 195, 247),   // sky_top
                Color.rgb(225, 245, 254),  // sky_bottom
                Shader.TileMode.CLAMP
        );
        bgPaint.setShader(skyGradient);
        canvas.drawRect(0, 0, surfaceWidth, surfaceHeight, bgPaint);

        synchronized (balloonLock) {
            // TODO (Day 4): Draw each balloon with oval + string + number
            for (Balloon b : balloons) {
                if (!b.isAlive()) continue;
                drawBalloon(canvas, b);
            }
        }
    }

    /**
     * Draw a single balloon: colored oval + string + point number.
     */
    private void drawBalloon(Canvas canvas, Balloon b) {
        float cx = b.getX();
        float cy = b.getY();
        float r = b.getRadius();

        // Balloon body (circle with slight 3D effect)
        balloonPaint.setColor(b.getColor());
        canvas.drawCircle(cx, cy, r, balloonPaint);

        // Highlight (smaller lighter circle for 3D look)
        balloonPaint.setColor(Color.argb(60, 255, 255, 255));
        canvas.drawCircle(cx - r * 0.25f, cy - r * 0.25f, r * 0.4f, balloonPaint);

        // Balloon string
        canvas.drawLine(cx, cy + r, cx, cy + r + 30, stringPaint);

        // Point number on balloon
        textPaint.setTextSize(r * 0.6f);
        canvas.drawText(String.valueOf(b.getPoints()), cx, cy + r * 0.2f, textPaint);
    }

    // ── Touch Handling ──

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!gameRunning) return false;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();

            synchronized (balloonLock) {
                for (int i = balloons.size() - 1; i >= 0; i--) {
                    Balloon b = balloons.get(i);
                    if (b.isAlive() && b.isTouched(touchX, touchY)) {
                        // Pop it!
                        b.setAlive(false);
                        score += b.getScoreValue();
                        if (hudCallback != null) {
                            hudCallback.onScoreChanged(score);
                        }
                        // TODO (Day 7): Pop animation
                        break; // only pop one per tap
                    }
                }
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    // ── Pause / Resume ──

    public void pauseGame() {
        gameRunning = false;
    }

    public void resumeGame() {
        gameRunning = true;
    }

    // ── Getters ──

    public int getSurfaceWidth() { return surfaceWidth; }
    public int getSurfaceHeight() { return surfaceHeight; }
}
