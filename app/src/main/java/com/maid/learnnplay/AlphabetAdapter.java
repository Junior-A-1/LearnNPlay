package com.maid.learnnplay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.ViewHolder> {

    private List<String> alphabetList;
    private OnAlphabetClickListener clickListener;
    private int selectedPosition = -1;
    private int[] colors;

    public AlphabetAdapter(List<String> alphabetList, OnAlphabetClickListener clickListener) {
        this.alphabetList = alphabetList;
        this.clickListener = clickListener;
        colors = generateRandomColors(alphabetList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alphabet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String alphabet = alphabetList.get(holder.getAdapterPosition());
        holder.alphabetText.setText(alphabet);

        if (selectedPosition == holder.getAdapterPosition()) {
            holder.itemView.setBackgroundColor(Color.RED);
            holder.alphabetText.setTextColor(Color.WHITE);
        } else {
            holder.itemView.setBackgroundColor(colors[holder.getAdapterPosition()]);
            holder.alphabetText.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
                clickListener.onAlphabetClick(alphabet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alphabetList.size();
    }

    private int[] generateRandomColors(int count) {
        int[] colors = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            colors[i] = Color.rgb(red, green, blue); // Generate RGB color value
        }
        return colors;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView alphabetText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alphabetText = itemView.findViewById(R.id.alphabetText);
        }
    }

    public interface OnAlphabetClickListener {
        void onAlphabetClick(String alphabet);
    }
}

