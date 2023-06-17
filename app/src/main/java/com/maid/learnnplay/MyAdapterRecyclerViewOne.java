package com.maid.learnnplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterRecyclerViewOne extends RecyclerView.Adapter<MyAdapterRecyclerViewOne.MyViewHolder> {

    Context context;
    ArrayList<ListItem> list;

    public MyAdapterRecyclerViewOne(Context context, ArrayList<ListItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListItem listItem = list.get(holder.getAdapterPosition());

        holder.imageView.setImageResource(listItem.imageView);
        holder.textView.setText(listItem.textView);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                navigateToActivity(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        Button button;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imgview_1);
            this.textView = itemView.findViewById(R.id.txtview_1);
            this.button = itemView.findViewById(R.id.button_id);
        }
    }

    private void navigateToActivity(int position)
    {
        switch (position)
        {
            case 0:
                Intent intentA = new Intent(context, ActivityA.class);
                context.startActivity(intentA);
                break;
            case 1:
                Intent intentB = new Intent(context, ActivityB.class);
                context.startActivity(intentB);
                break;
            case 2:
                Intent intentC = new Intent(context, ActivityC.class);
                context.startActivity(intentC);
                break;
            case 3:
                Intent intentD = new Intent(context, ActivityD.class);
                context.startActivity(intentD);
                break;
        }
    }
}
