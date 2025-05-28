package com.eisenreich.axolotladventure;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {
    private GameView gameView;
    private Button btnLeft, btnRight, btnJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = findViewById(R.id.view_game);
        btnLeft = findViewById(R.id.button_left);
        btnRight = findViewById(R.id.button_right);
        btnJump = findViewById(R.id.button_jump);

        btnLeft.setOnTouchListener((v, event) -> {
            gameView.setMovingLeft(event.getAction() != android.view.MotionEvent.ACTION_UP);
            return true;
        });

        btnRight.setOnTouchListener((v, event) -> {
            gameView.setMovingRight(event.getAction() != android.view.MotionEvent.ACTION_UP);
            return true;
        });

        btnJump.setOnClickListener(v -> gameView.jump());
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
