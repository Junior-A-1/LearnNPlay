package com.maid.learnnplay;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    private List<Integer> gameScores1, gameScores2, gameScores3;
    private SharedPreferences preferences;
    private TextView totalScoreTextView1, totalScoreTextView2, totalScoreTextView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        totalScoreTextView1 = findViewById(R.id.totalScoreTextView1);
        totalScoreTextView2 = findViewById(R.id.totalScoreTextView2);
        totalScoreTextView3 = findViewById(R.id.totalScoreTextView3);



        preferences = getSharedPreferences("GameScores_" + FirebaseAuth.getInstance().getCurrentUser().getUid(), MODE_PRIVATE);


        gameScores1 = retrieveScores("gameScores1");
        gameScores2 = retrieveScores("gameScores2");
        gameScores3 = retrieveScores("gameScores3");

        int scoreB = getIntent().getIntExtra("BScore", 0);
        int scoreC = getIntent().getIntExtra("CScore", 0);
        int scoreD = getIntent().getIntExtra("DScore", 0);


        if (!gameScores1.contains(scoreB)) {
            gameScores1.add(scoreB);
        }
        if (!gameScores2.contains(scoreC)) {
            gameScores2.add(scoreC);
        }
        if (!gameScores3.contains(scoreD)) {
            gameScores3.add(scoreD);
        }


        TextView scoresTextView1 = findViewById(R.id.scoresTextView1);
        TextView scoresTextView2 = findViewById(R.id.scoresTextView2);
        TextView scoresTextView3 = findViewById(R.id.scoresTextView3);


        StringBuilder stringBuilder1 = new StringBuilder();
        if (gameScores1.isEmpty()) {
            stringBuilder1.append("No scores available for Game 1");
        } else {
            stringBuilder1.append("Game 1: ").append(gameScores1.get(0)).append("\n");
            for (int i = 1; i < gameScores1.size(); i++) {
                stringBuilder1.append("Game ").append(i + 1).append(": ").append(gameScores1.get(i)).append("\n");
            }
        }

        StringBuilder stringBuilder2 = new StringBuilder();
        if (gameScores2.isEmpty()) {
            stringBuilder2.append("No scores available for Game 2");
        } else {
            stringBuilder2.append("Game 1: ").append(gameScores2.get(0)).append("\n");
            for (int i = 1; i < gameScores2.size(); i++) {
                stringBuilder2.append("Game ").append(i + 1).append(": ").append(gameScores2.get(i)).append("\n");
            }
        }

        StringBuilder stringBuilder3 = new StringBuilder();
        if (gameScores3.isEmpty()) {
            stringBuilder3.append("No scores available for Game 3");
        } else {
            stringBuilder3.append("Game 1: ").append(gameScores3.get(0)).append("\n");
            for (int i = 1; i < gameScores3.size(); i++) {
                stringBuilder3.append("Game ").append(i + 1).append(": ").append(gameScores3.get(i)).append("\n");
            }
        }

        scoresTextView1.setText(stringBuilder1.toString());
        scoresTextView2.setText(stringBuilder2.toString());
        scoresTextView3.setText(stringBuilder3.toString());


        int totalScore1 = calculateTotalScore(gameScores1);
        int totalScore2 = calculateTotalScore(gameScores2);
        int totalScore3 = calculateTotalScore(gameScores3);

        totalScoreTextView1.setText("Total Score: " + totalScore1);
        totalScoreTextView2.setText("Total Score: " + totalScore2);
        totalScoreTextView3.setText("Total Score: " + totalScore3);

        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child("correctMe").setValue(totalScore1);
        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child("multyMe").setValue(totalScore2);
        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child("shapeMe").setValue(totalScore3);

    }
    private int calculateTotalScore(List<Integer> scores) {
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }
        return totalScore;
    }


    @Override
    protected void onPause() {
        super.onPause();
        saveScores("gameScores1", gameScores1);
        saveScores("gameScores2", gameScores2);
        saveScores("gameScores3", gameScores3);
    }

    private List<Integer> retrieveScores(String key) {
        String scoresString = preferences.getString(key, "");
        List<Integer> scores = new ArrayList<>();
        if (!scoresString.isEmpty()) {
            String[] scoresArray = scoresString.split(",");
            for (String score : scoresArray) {
                scores.add(Integer.parseInt(score));
            }
        }
        return scores;
    }

    private void saveScores(String key, List<Integer> scores) {
        SharedPreferences userPreferences = getSharedPreferences("GameScores_" + FirebaseAuth.getInstance().getCurrentUser().getUid(), MODE_PRIVATE);
        StringBuilder scoresStringBuilder = new StringBuilder();
        for (Integer score : scores) {
            scoresStringBuilder.append(score).append(",");
        }
        String scoresString = scoresStringBuilder.toString();
        if (!scoresString.isEmpty()) {
            scoresString = scoresString.substring(0, scoresString.length() - 1);
        }
        userPreferences.edit().putString(key, scoresString).apply();
    }


}