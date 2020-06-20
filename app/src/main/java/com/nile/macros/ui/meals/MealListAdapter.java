package com.nile.macros.ui.meals;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cocosw.bottomsheet.BottomSheet;
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
        // parent is the recycler view
        // so parent.getContext() returns the activity that the recycler view is in
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_food, parent, false);
        final ImageView btnShowMenu = v.findViewById(R.id.btn_show_menu);
        final Context context = parent.getContext();  // Rethink what context should be used
        // for the popup menu below. Does it affect database access? Should it be declared final?
        final ViewGroup some = parent;

//        btnShowMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new BottomSheet.Builder((Activity) context).title("title").sheet(R.menu.card_menu).listener(new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case R.id.thing:
//                                // do something
//                                break;
//                        }
//                    }
//                }).show();
//            }
//        });

        btnShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealsFragment.thing.show();
            }
        });

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
        EditText foodName;

        public ViewHolder(CardView card) {
            super(card);
            cardView = card;
            foodName = card.findViewById(R.id.food_name);
        }
    }
}
