package com.eisenreich.axolotladventure;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
public class LevelProgressView extends LinearLayout {

    private TextView levelTitle;
    private TextView statusText;
    private TextView timeText;
    private TextView collectiblesText;

    public LevelProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_level_progress, this, true);

        levelTitle = findViewById(R.id.textView_levelTitle);
        statusText = findViewById(R.id.textView_status);
        timeText = findViewById(R.id.textView_time);
        collectiblesText = findViewById(R.id.textView_collectibles);
    }

    public void setLevelInfo(int levelNum, boolean completed, long timeMillis, int collectibles) {
        levelTitle.setText("Level " + levelNum);
        if (completed) {
            statusText.setText("Rescued!");
            timeText.setText("Time: " + DateUtils.formatElapsedTime(timeMillis / 1000));
            collectiblesText.setText("Collectibles: " + collectibles);
            setBackgroundColor(Color.parseColor("#CCFFCC"));
        } else {
            statusText.setText("Not Completed");
            timeText.setText("Time: -");
            collectiblesText.setText("Collectibles: -");
            setBackgroundColor(Color.parseColor("#DDDDDD"));
        }
    }
}
