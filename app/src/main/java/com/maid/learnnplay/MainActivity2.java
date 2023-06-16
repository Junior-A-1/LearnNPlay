package com.maid.learnnplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView,recyclerView1,recyclerView2;
    ArrayList<ListItem> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recycler_view);

        lists = new ArrayList<>();

        lists.add(new ListItem(R.drawable.game1,"Piano Alphabet"));
        lists.add(new ListItem(R.drawable.game2,"Correct Me"));
        lists.add(new ListItem(R.drawable.game3,"PLAY"));
        lists.add(new ListItem(R.drawable.game,"SHAPE ME"));


        MyAdapterRecyclerViewOne adapter = new MyAdapterRecyclerViewOne(this,lists);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);//Inflated the menu with the asset on it (profile_menu)
        return true;
    }

    //behaving as a button for profile table on the MainActivity2
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.menu_item_profile)//getting the item that is being clicked id if yes then open ProfileActivity
        {
            startActivity(new Intent(MainActivity2.this,ProfileActivity.class));//Moving to ProfileAvtivity
        }

        return super.onOptionsItemSelected(item);
    }
}