package com.yuvraj.balloonpop.model;

/**
 * Enum representing different types of balloons in the game.
 * Each type has different point values and visual appearance.
 */
public enum BalloonType {
    /** Standard balloon — normal points */
    NORMAL(1.0f),
    /** Bomb balloon — tapping this penalizes the player */
    BOMB(0f),
    /** Bonus golden balloon — extra points */
    BONUS(5.0f);

    private final float pointMultiplier;

    BalloonType(float pointMultiplier) {
        this.pointMultiplier = pointMultiplier;
    }

    public float getPointMultiplier() {
        return pointMultiplier;
    }
}
