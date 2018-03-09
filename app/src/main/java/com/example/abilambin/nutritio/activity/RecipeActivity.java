package com.example.abilambin.nutritio.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.fragment.HeaderRecipeFragment;
import com.example.abilambin.nutritio.fragment.IngredientListFragment;
import com.example.abilambin.nutritio.fragment.IntakesFragment;
import com.example.abilambin.nutritio.fragment.MealIngredientListFragment;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        addFragment(new IntakesFragment());
        addFragment(new MealIngredientListFragment());
    }

    /**
     * Ajoute le fragment en paramètre à la vue en cours (à la suite des autres fragments déjà présents)
     * @param fragment le fragment à ajouter
     */
    private void addFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.container,fragment)
                .addToBackStack("frag")
                .commit();
    }
}
