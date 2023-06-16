package com.maid.learnnplay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.maid.learnnplay.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LetterGameActivity extends AppCompatActivity {

    private List<String> letters;
    private TextView questionTextView;
    private GridLayout letterGridLayout;
    private int score = 0;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        letters = generateLetters();

        questionTextView = findViewById(R.id.questionTextView);
        letterGridLayout = findViewById(R.id.letterGridLayout);
        scoreTextView = findViewById(R.id.scoreTextView);
        Button restartButton = findViewById(R.id.restartButton);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });

        Collections.shuffle(letters);

        String question = letters.get(0).toLowerCase();
        questionTextView.setText(question);

        for (String letter : letters) {
            createLetterButton(letter);
        }
    }

    private List<String> generateLetters() {
        List<String> list = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            list.add(String.valueOf(c));
        }
        return list;
    }

    private void createLetterButton(final String letter) {
        TextView letterTextView = new TextView(this);
        letterTextView.setText(letter);
        letterTextView.setTextSize(20);
        letterTextView.setGravity(Gravity.CENTER);
        letterTextView.setPadding(16, 16, 16, 16);
        letterTextView.setBackgroundColor(Color.WHITE);
        letterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedLetter = ((TextView) view).getText().toString();
                if (selectedLetter.equalsIgnoreCase(letters.get(0))) {
                    view.setBackgroundColor(Color.GREEN);
                    letters.remove(0);
                    score++;
                    scoreTextView.setText("Score: " + score);
                    if (letters.size() > 0) {
                        String question = letters.get(0).toLowerCase();
                        questionTextView.setText(question);
                    } else {
                        questionTextView.setText("Game Over");
                    }
                } else {
                    view.setBackgroundColor(Color.RED);
                }
            }
        });

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = GridLayout.LayoutParams.WRAP_CONTENT;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.setMargins(16, 16, 16, 16);
        letterGridLayout.addView(letterTextView, params);
    }

    private void restartGame() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
