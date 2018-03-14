package com.example.abilambin.nutritio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientActivity extends AppCompatActivity {


    @BindView(R.id.ingredientTitle)
    TextView title;

    @BindView(R.id.ingredientCategory)
    TextView category;

    @BindView(R.id.ingredientBrand)
    TextView brand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        ButterKnife.bind(this);


    }


    @Override
    protected void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();
        Ingredient ingredient = ((IngredientEntry) bundle.get("ingredient")).getIngredient();

        title.setText(ingredient.getName());
        category.setText(ingredient.getCategoryText());
        brand.setText(ingredient.getBrand());
    }

}
