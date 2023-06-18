package com.maid.learnnplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityD extends AppCompatActivity {

    private ImageView shapeImageView;
    private Button rectangleButton;
    private Button circleButton;
    private Button triangleButton;
    private TextView scoreTextView;

    ImageButton imgbtn;
    private List<Integer> shapeList;
    private Random random;
    private int currentShape;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);

        shapeImageView = findViewById(R.id.shapeImageView);
        rectangleButton = findViewById(R.id.rectangleButton);
        circleButton = findViewById(R.id.circleButton);
        triangleButton = findViewById(R.id.triangleButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        imgbtn = findViewById(R.id.imgbtn1);


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityD.this,MainActivity2.class);
                intent.putExtra("ScoreD",score);
                startActivity(intent);
            }
        });


        // Initialize the list of shapes
        shapeList = new ArrayList<>();
        shapeList.add(R.drawable.shape3);
        shapeList.add(R.drawable.shape1);
        shapeList.add(R.drawable.shape2);



        random = new Random();
        setRandomShape();

        rectangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.drawable.shape3);
            }
        });

        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.drawable.shape1);
            }
        });

        triangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.drawable.shape2);
            }
        });
    }

    private void setRandomShape() {
        // Get a random shape from the list
        int index = random.nextInt(shapeList.size());
        currentShape = shapeList.get(index);

        // Set the shape drawable to the image view
        shapeImageView.setImageResource(currentShape);
    }

    private void checkAnswer(int selectedShape) {
        if (selectedShape == currentShape) {
            showToast("Correct!");
            incrementScore();
        } else {
            showToast("Wrong!");
        }

        setRandomShape();
    }

    private void incrementScore() {
        score++;
        scoreTextView.setText("Score: " + score);

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
