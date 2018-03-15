package com.example.abilambin.nutritio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.fragment.IntakesValuesFragment;
import com.example.abilambin.nutritio.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientActivity extends AppCompatActivity {

    private IngredientEntry ingredientEntry;

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
        this.ingredientEntry = (IngredientEntry) bundle.get("ingredient");

        Ingredient ingredient = ingredientEntry.getIngredient();

        title.setText(ingredient.getName());
        category.setText(Html.fromHtml("<b>Catégorie : </b>" +ingredient.getCategoryText()));
        String marque = ingredient.getBrand();
        if (marque != null) {
            brand.setText(Html.fromHtml("<b>Marque : </b>"+marque));
        }

        // ON AJOUTE LE FRAGMENT INTAKES_VALUES
        IntakesValuesFragment fg = new IntakesValuesFragment();
        List<IngredientEntry> entries = new ArrayList<>();
        entries.add(ingredientEntry);
        fg.setIngredientEntries(entries);
        Utils.addFragment(fg, getFragmentManager());

    }


    @Override
    public void onBackPressed() {
        finish();
    }








}
