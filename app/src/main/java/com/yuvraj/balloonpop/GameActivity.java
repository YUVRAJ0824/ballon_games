package com.yuvraj.balloonpop;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yuvraj.balloonpop.engine.GameSurface;

/**
 * GameActivity — hosts the game SurfaceView and HUD overlays.
 *
 * Receives the game mode via Intent extra ("game_mode").
 * For now, only "classic_pop" is supported.
 */
public class GameActivity extends AppCompatActivity {

    private GameSurface gameSurface;
    private TextView tvScore;
    private TextView tvLives;
    private TextView tvLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameSurface = findViewById(R.id.gameSurface);
        tvScore = findViewById(R.id.tvScore);
        tvLives = findViewById(R.id.tvLives);
        tvLevel = findViewById(R.id.tvLevel);

        // Read game mode from intent
        String gameMode = getIntent().getStringExtra("game_mode");

        // Set up HUD callback so game surface can update score/lives on UI thread
        gameSurface.setHudCallback(new GameSurface.HudCallback() {
            @Override
            public void onScoreChanged(final int score) {
                runOnUiThread(() ->
                    tvScore.setText(String.format(getString(R.string.score_label), score))
                );
            }

            @Override
            public void onLivesChanged(final int lives) {
                runOnUiThread(() ->
                    tvLives.setText(String.format(getString(R.string.lives_label), lives))
                );
            }

            @Override
            public void onLevelChanged(final int level) {
                runOnUiThread(() ->
                    tvLevel.setText(String.format(getString(R.string.level_label), level))
                );
            }

            @Override
            public void onGameOver(final int finalScore) {
                runOnUiThread(() -> {
                    // TODO (Day 14): Show GameOverDialog
                    finish(); // For now, just go back to menu
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gameSurface != null) {
            gameSurface.pauseGame();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gameSurface != null) {
            gameSurface.resumeGame();
        }
    }
}
