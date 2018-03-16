package com.example.abilambin.nutritio.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.utils.Intakes;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class IntakesValuesFragment extends Fragment {

    private List<IngredientEntry> ingredientEntries;

    // INTAKES
    @BindView(R.id.energy)
    TextView energy;

    @BindView(R.id.proteins)
    TextView proteins;
    @BindView(R.id.carbs)
    TextView carbs;
    @BindView(R.id.sugar)
    TextView sugar;
    @BindView(R.id.fat)
    TextView fat;
    @BindView(R.id.saturatedFat)
    TextView saturatedFat;
    @BindView(R.id.fibres)
    TextView fibres;


    public List<IngredientEntry> getIngredientEntries() {
        return ingredientEntries;
    }

    public void setIngredientEntries(List<IngredientEntry> ingredientEntries) {
        this.ingredientEntries = ingredientEntries;
    }

    public IntakesValuesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intakes_values, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showAllIntakes();
    }

    private void showAllIntakes() {

        Intakes intakes = new Intakes();

        for (IngredientEntry entry : ingredientEntries) {
            entry.setAmount(100);
            intakes.addMoreIntakes(entry);
        }

        showOneIntakes(intakes.getEnergy(), energy, "kcal");
        showOneIntakes(intakes.getProtein(), proteins, "g");
        showOneIntakes(intakes.getCarbohydrate(), carbs, "g");
        showOneIntakes(intakes.getSugar(), sugar, "g");
        showOneIntakes(intakes.getFat(), fat, "g");
        showOneIntakes(intakes.getSaturatedFat(), saturatedFat, "g");
        showOneIntakes(intakes.getFibre(), fibres, "g");
    }

    private void showOneIntakes(int intake, TextView textView, String unit) {
        final NumberFormat instance = NumberFormat.getNumberInstance();
        instance.setMinimumFractionDigits(0);
        instance.setMaximumFractionDigits(2);

        textView.setText(instance.format(intake)+unit);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
    }
}
