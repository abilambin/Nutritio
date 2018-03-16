package com.example.abilambin.nutritio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.fragment.HeaderRecipeFragment;
import com.example.abilambin.nutritio.fragment.IntakesValuesFragment;
import com.example.abilambin.nutritio.fragment.RecipeIngredientListFragment;
import com.example.abilambin.nutritio.utils.Utils;

public class RecipeActivity extends AppCompatActivity {

    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Bundle bundle = getIntent().getExtras();
        recipe = (Recipe) bundle.get("recipe");

        // ON AJOUTE LES FRAGMENTS
        Utils.addFragment(new HeaderRecipeFragment(), getFragmentManager());

        IntakesValuesFragment fg = new IntakesValuesFragment();
        fg.setIngredientEntries(recipe.getIngredientEntries());
        Utils.addFragment(fg, getFragmentManager());
        Utils.addFragment(new RecipeIngredientListFragment(), getFragmentManager());
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
