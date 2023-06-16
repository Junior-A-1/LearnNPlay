package com.maid.learnnplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityB extends AppCompatActivity {

    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        restartButton = findViewById(R.id.restartButton);

        Intent intent = new Intent(this, LetterGameActivity.class);
        startActivity(intent);
        finish();

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Restart the game
                startActivity(intent);
                finish();
            }
        });
    }
}
