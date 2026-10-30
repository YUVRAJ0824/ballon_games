package com.yuvraj.balloonpop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main Menu Activity — the first screen the player sees.
 *
 * Displays:
 *  - App title & subtitle
 *  - High score
 *  - 4 game mode buttons (only Classic Pop is unlocked initially)
 */
public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "balloon_pop_prefs";
    private static final String KEY_HIGH_SCORE = "high_score_classic";

    private TextView tvHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tvHighScore = findViewById(R.id.tvHighScore);
        Button btnClassicPop = findViewById(R.id.btnClassicPop);

        // Launch Classic Pop game
        btnClassicPop.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("game_mode", "classic_pop");
            startActivity(intent);
        });

        // Other buttons are disabled/locked for now
        // They'll be unlocked as we build each mini-game
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHighScore();
    }

    private void loadHighScore() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int highScore = prefs.getInt(KEY_HIGH_SCORE, 0);
        tvHighScore.setText(String.format(getString(R.string.high_score_label), highScore));
    }
}
