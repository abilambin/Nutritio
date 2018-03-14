package com.example.abilambin.nutritio.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.fragment.HeaderRecipeFragment;
import com.example.abilambin.nutritio.fragment.IntakesFragment;
import com.example.abilambin.nutritio.fragment.MealIngredientListFragment;

public class RecipeActivity extends AppCompatActivity {

    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Bundle bundle = getIntent().getExtras();
        recipe = (Recipe) bundle.get("recipe");

        addFragment(new HeaderRecipeFragment());
        final IntakesFragment fg = new IntakesFragment();
        fg.setIngredientEntries(recipe.getIngredientEntries());
        addFragment(fg);
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
                .commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
