package com.eisenreich.axolotladventure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HubActivity extends AppCompatActivity {
    private LevelProgressView level1View, level2View, level3View;
    private Button toMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        level1View = findViewById(R.id.level1Progress);
        level2View = findViewById(R.id.level2Progress);
        level3View = findViewById(R.id.level3Progress);
        toMenuButton = findViewById(R.id.button_toMenu);
        toMenuButton.setOnClickListener(view -> {
            Intent intent = new Intent(HubActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        updateLevelView(level1View, prefs, 1);
        updateLevelView(level2View, prefs, 2);
        updateLevelView(level3View, prefs, 3);
    }

    private void updateLevelView(LevelProgressView view, SharedPreferences prefs, int levelNum) {
        boolean completed = prefs.getBoolean("level" + levelNum + "_completed", false);
        long time = prefs.getLong("level" + levelNum + "_time", 0);
        int collectibles = prefs.getInt("level" + levelNum + "_collectables", 0);
        view.setLevelInfo(levelNum, completed, time, collectibles);
    }
}