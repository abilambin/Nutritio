package com.example.abilambin.nutritio.fragment;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.Meal;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntakesFragment extends Fragment {


    protected ArrayList<Ingredient> ingredients;

    protected ArrayList<Meal> meals;

    protected int quantity = 100;



    @BindView(R.id.proteinesProgressBar)
    ProgressBar proteinesProgressBar;

    @BindView(R.id.proteinesPctTextView)
    TextView proteinesPctTextView;


    @BindView(R.id.glucidesProgressBar)
    ProgressBar glucidesProgressBar;

    @BindView(R.id.glucidesPctTextView)
    TextView glucidesPctTextView;

    @BindView(R.id.sucreProgressBar)
    ProgressBar sucreProgressBar;

    @BindView(R.id.lipidesProgressBar)
    ProgressBar lipidesProgressBar;

    @BindView(R.id.lipidesPctTextView)
    TextView lipidesPctTextView;

    @BindView(R.id.agsProgressBar)
    ProgressBar agsProgressBar;

    @BindView(R.id.fibresProgressBar)
    ProgressBar fibresProgressBar;

    @BindView(R.id.fibresPctTextView)
    TextView fibresPctTextView;


    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public IntakesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intakes, container, false);
        ButterKnife.bind(this, view);

        generateIntakes();


        return view;
    }

    public void generateIntakes() {
        if (meals != null && !meals.isEmpty()) {
            //Parcourir les ingrédient et leur associer les valeurs

        } else if (ingredients != null  && !ingredients.isEmpty()){
            setIntakes(ingredients);
        }
    }

    private void setIntakes(ArrayList<Ingredient> ingredients) {

        float prot=0;
        float carb=0;
        float sugar=0;
        float fat=0;
        float sf=0;
        float fibre = 0;

        for (Ingredient ingredient : ingredients) {
            prot += ingredient.getProtein();
            carb += ingredient.getCarbohydrate();
            sugar += ingredient.getSugar();
            fat += ingredient.getFat();
            sf += ingredient.getSaturatedFat();
            fibre += ingredient.getFibre();
        }

        prot = valueCalcul(prot,quantity);
        carb = valueCalcul(carb,quantity);
        sugar = valueCalcul(sugar,quantity);
        fat = valueCalcul(fat,quantity);
        sf = valueCalcul(sf,quantity);
        fibre = valueCalcul(fibre,quantity);

        proteinesProgressBar.setProgress(percent(prot, 100));
        proteinesPctTextView.setText(""+prot);

        glucidesProgressBar.setProgress(percent(carb, 100));
        glucidesPctTextView.setText(""+carb);

        sucreProgressBar.setProgress(percent(sugar, 100));

        lipidesProgressBar.setProgress(percent(fat, 100));
        lipidesPctTextView.setText(""+fat);

        agsProgressBar.setProgress(percent(sf, 100));

        fibresProgressBar.setProgress(percent(fibre, 100));
        fibresPctTextView.setText(""+fibre);
    }

    private static int percent(float val, float obj){
        float result = (val/obj)*100;
        return Math.round(result);
    }

    private static int valueCalcul(float val, float quantity){
        float result = val*quantity/100;
        return Math.round(result);
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
    }

}
