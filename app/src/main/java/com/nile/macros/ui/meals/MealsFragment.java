package com.nile.macros.ui.meals;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.StackView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.nile.macros.R;

import java.util.ArrayList;
import java.util.List;

public class MealsFragment extends Fragment {

    private MealsViewModel mealsViewModel;

    RecyclerView recyclerView;
    MealListAdapter adapter;
//    StackAdapter adapter;

    StackView stackView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MealListAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mealsViewModel =
                ViewModelProviders.of(this).get(MealsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_meals, container, false);
//        final TextView textView = root.findViewById(R.id.text_meals);
//
//        mealsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

//        HorizontalBarChart chart = root.findViewById(R.id.bar_chart);
//
//        List<BarEntry> entries = new ArrayList<BarEntry>();
//        entries.add(new BarEntry(0f, 20f));
//        entries.add(new BarEntry(1f, 40f));
//
//        BarDataSet dataSet = new BarDataSet(entries, "Macros");
//        BarData data = new BarData(dataSet);
//        chart.setData(data);
//        chart.setFitBars(true);

        // Recycler view
        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        stackView = root.findViewById(R.id.stack);
//        stackView.setAdapter(adapter);

        return root;
    }
}