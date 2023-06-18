package com.maid.learnnplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActivityC extends AppCompatActivity {

    private TextView questionTextView;
    private Button[] answerButtons;
    private int[] answers;
    private int correctAnswer;
    private int score;
    ImageButton imgbtn;

    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        questionTextView = findViewById(R.id.questionTextView);
        answerButtons = new Button[4];
        answerButtons[0] = findViewById(R.id.answerButton1);
        answerButtons[1] = findViewById(R.id.answerButton2);
        answerButtons[2] = findViewById(R.id.answerButton3);
        answerButtons[3] = findViewById(R.id.answerButton4);

        scoreTextView = findViewById(R.id.scoreTextView);
        imgbtn = findViewById(R.id.imgbtn1);


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityC.this,MainActivity2.class);
                intent.putExtra("ScoreC",score);
                startActivity(intent);
            }
        });

        generateQuestion();
        for (Button button : answerButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedAnswer = Integer.parseInt(((Button) v).getText().toString());
                    checkAnswer(selectedAnswer);
                    generateQuestion();
                }
            });
        }
    }

    private void generateQuestion() {
        Random random = new Random();
        int number1 = random.nextInt(9) + 1; // Random number between 1 and 9
        int number2 = random.nextInt(9) + 1;
        correctAnswer = number1 * number2;

        questionTextView.setText(number1 + " * " + number2 + " = ?");

        answers = new int[4];
        for (int i = 0; i < 4; i++) {
            answers[i] = random.nextInt(81) + 1; // Random number between 1 and 81

            // Make sure the answers are unique and not equal to the correct answer
            while (answers[i] == correctAnswer || containsDuplicate(answers, i)) {
                answers[i] = random.nextInt(81) + 1;
            }
        }
        int correctAnswerButtonIndex = random.nextInt(4);
        answerButtons[correctAnswerButtonIndex].setText(String.valueOf(correctAnswer));

        // Assign the other answers to the remaining buttons
        int answerIndex = 0;
        for (int i = 0; i < 4; i++) {
            if (i != correctAnswerButtonIndex) {
                answerButtons[i].setText(String.valueOf(answers[answerIndex]));
                answerIndex++;
            }
        }
    }

    private boolean containsDuplicate(int[] array, int index) {
        for (int i = 0; i < index; i++) {
            if (array[i] == array[index]) {
                return true;
            }
        }
        return false;
    }

    private void checkAnswer(int selectedAnswer) {
        if (selectedAnswer == correctAnswer) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            incrementScore();
        } else {
            Toast.makeText(this, "Wrong! The correct answer is " + correctAnswer, Toast.LENGTH_SHORT).show();
        }
    }

    private void incrementScore() {
        score++;
        scoreTextView.setText("Score: " + score);
    }
}
