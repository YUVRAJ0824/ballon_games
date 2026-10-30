package com.yuvraj.balloonpop.model;

import android.graphics.Color;

import java.util.Random;

/**
 * Represents a single balloon entity in the game.
 *
 * Each balloon has:
 * - Position (x, y) on screen
 * - A rising speed (pixels per second)
 * - A radius that can grow while the player holds it
 * - A random color
 * - A point value displayed as a number on the balloon
 * - A type (NORMAL, BOMB, BONUS)
 * - An alive flag (false after being popped)
 */
public class Balloon {

    // ── Position & Movement ──
    private float x;           // center X
    private float y;           // center Y
    private float speed;       // pixels per second (upward)
    private float swayOffset;  // horizontal sway phase

    // ── Size ──
    private float radius;      // current radius in pixels
    private float maxRadius;   // maximum radius this balloon can grow to
    private static final float MIN_RADIUS = 40f;
    private static final float MAX_RADIUS_CAP = 120f;

    // ── Appearance ──
    private int color;         // balloon fill color
    private int points;        // point value shown on the balloon

    // ── State ──
    private BalloonType type;
    private boolean alive;     // true while on screen, false after popped or escaped
    private boolean popping;   // true during pop animation
    private float popProgress; // 0.0 → 1.0 pop animation progress

    // ── Static helpers ──
    private static final Random sRandom = new Random();

    /** Pre-defined vibrant balloon colors */
    private static final int[] COLORS = {
            Color.rgb(231, 76, 60),    // Red
            Color.rgb(46, 204, 113),   // Green
            Color.rgb(52, 152, 219),   // Blue
            Color.rgb(155, 89, 182),   // Purple
            Color.rgb(241, 196, 15),   // Yellow
            Color.rgb(230, 126, 34),   // Orange
            Color.rgb(26, 188, 156),   // Teal
            Color.rgb(236, 72, 153),   // Pink
    };

    /**
     * Default constructor — call {@link #reset} to initialize for reuse (object pooling).
     */
    public Balloon() {
        this.alive = false;
        this.popping = false;
        this.popProgress = 0f;
    }

    /**
     * Reset this balloon for reuse from the object pool.
     *
     * @param screenWidth  width of the game surface
     * @param screenHeight height of the game surface
     * @param type         the type of balloon to spawn
     */
    public void reset(int screenWidth, int screenHeight, BalloonType type) {
        this.type = type;

        // Random radius between MIN and MAX
        this.maxRadius = MIN_RADIUS + sRandom.nextFloat() * (MAX_RADIUS_CAP - MIN_RADIUS);
        this.radius = MIN_RADIUS + sRandom.nextFloat() * (maxRadius - MIN_RADIUS) * 0.5f;

        // Random X within screen bounds, Y starts just below screen
        this.x = radius + sRandom.nextFloat() * (screenWidth - 2 * radius);
        this.y = screenHeight + radius + sRandom.nextFloat() * 100;

        // Speed: smaller balloons rise faster (more points)
        this.speed = 80f + (MAX_RADIUS_CAP - radius) * 1.5f;

        // Sway
        this.swayOffset = sRandom.nextFloat() * (float) (2 * Math.PI);

        // Color
        if (type == BalloonType.BOMB) {
            this.color = Color.rgb(40, 40, 40); // dark/black for bombs
        } else if (type == BalloonType.BONUS) {
            this.color = Color.rgb(255, 215, 0); // gold
        } else {
            this.color = COLORS[sRandom.nextInt(COLORS.length)];
        }

        // Points: smaller = more points (reward precision)
        this.points = Math.max(5, (int) ((MAX_RADIUS_CAP - radius) / 5f) * 5);

        // State
        this.alive = true;
        this.popping = false;
        this.popProgress = 0f;
    }

    // ── Game Logic ──

    /**
     * Check if a touch point is inside this balloon.
     *
     * @param touchX X coordinate of touch
     * @param touchY Y coordinate of touch
     * @return true if touch is within the balloon's radius
     */
    public boolean isTouched(float touchX, float touchY) {
        float dx = touchX - x;
        float dy = touchY - y;
        return (dx * dx + dy * dy) <= (radius * radius);
    }

    /**
     * Start the pop animation.
     */
    public void pop() {
        this.popping = true;
        this.popProgress = 0f;
    }

    /**
     * Check if this balloon has escaped above the top of the screen.
     *
     * @return true if the balloon is above the screen (y + radius < 0)
     */
    public boolean hasEscaped() {
        return y + radius < 0;
    }

    /**
     * Calculate the score this balloon awards when popped.
     * Smaller balloons give more points.
     *
     * @return the point value
     */
    public int getScoreValue() {
        return (int) (points * type.getPointMultiplier());
    }

    // ── Getters & Setters ──

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }

    public float getSwayOffset() { return swayOffset; }

    public float getRadius() { return radius; }
    public void setRadius(float radius) { this.radius = radius; }

    public float getMaxRadius() { return maxRadius; }

    public int getColor() { return color; }

    public int getPoints() { return points; }

    public BalloonType getType() { return type; }

    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }

    public boolean isPopping() { return popping; }
    public void setPopping(boolean popping) { this.popping = popping; }

    public float getPopProgress() { return popProgress; }
    public void setPopProgress(float popProgress) { this.popProgress = popProgress; }

    public static float getMinRadius() { return MIN_RADIUS; }
    public static float getMaxRadiusCap() { return MAX_RADIUS_CAP; }
}
