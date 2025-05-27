package com.eisenreich.axolotladventure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        prefs = getSharedPreferences("GameData", MODE_PRIVATE);
        boolean hasCompletedAny = prefs.getBoolean("level1_completed", false)
                || prefs.getBoolean("level2_completed", false)
                || prefs.getBoolean("level3_completed", false);
        Button startButton = findViewById(R.id.button_startGame);
        startButton.setOnClickListener(view -> {
            int currentLevel = prefs.getInt("current_level", 1);
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("level", currentLevel);
            startActivity(intent);
        });
        Button hubButton = findViewById(R.id.button_hub);
        hubButton.setOnClickListener(view -> {
            startActivity(new Intent(this, HubActivity.class));
        });
        Button selectLevelButton = findViewById(R.id.button_selectLevel);
        selectLevelButton.setVisibility(hasCompletedAny ? View.VISIBLE : View.GONE);
        selectLevelButton.setOnClickListener(view -> {
            startActivity(new Intent(this, LevelSelectActivity.class));
        });
    }
}