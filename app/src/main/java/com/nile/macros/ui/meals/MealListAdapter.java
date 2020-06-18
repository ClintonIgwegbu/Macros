package com.nile.macros.ui.meals;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nile.macros.R;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.ViewHolder> {

    static String[] fakeMeals = new String[] {
            "Chicken",
            "Fish",
            "Cheese",
            "Asparagus",
            "Milk",
            "Bread"
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate CardView
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_food, parent, false);

        // Wrap it in a ViewHolder
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foodName.setText(fakeMeals[position]);
    }

    @Override
    public int getItemCount() {
        return fakeMeals.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView foodName;

        public ViewHolder(CardView card) {
            super(card);
            cardView = card;
            foodName = card.findViewById(R.id.food_name);
        }
    }
}
