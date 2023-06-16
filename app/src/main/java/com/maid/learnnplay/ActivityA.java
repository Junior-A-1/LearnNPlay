package com.maid.learnnplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityA extends AppCompatActivity implements AlphabetAdapter.OnAlphabetClickListener {

    private RecyclerView alphabetRecyclerView;
    private AlphabetAdapter alphabetAdapter;
    private List<String> alphabetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        alphabetRecyclerView = findViewById(R.id.alphabetRecyclerView);
        alphabetList = generateAlphabetList();

        alphabetAdapter = new AlphabetAdapter(alphabetList, this);
        alphabetRecyclerView.setAdapter(alphabetAdapter);

        // Use GridLayoutManager with 3 columns
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        alphabetRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onAlphabetClick(String alphabet) {
        // Handle the selected alphabet click
        // You can change the color or update the TextView as desired
        TextView selectedAlphabetText = findViewById(R.id.selectedAlphabetText);
        selectedAlphabetText.setText(alphabet);
        selectedAlphabetText.setTextColor(Color.RED);
    }

    private List<String> generateAlphabetList() {
        List<String> list = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            list.add(String.valueOf(c));
        }
        return list;
    }
}
