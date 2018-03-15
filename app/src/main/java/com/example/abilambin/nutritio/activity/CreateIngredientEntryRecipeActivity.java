package com.example.abilambin.nutritio.activity;

import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.RecipeRestCaller;

/**
 * Created by serial on 09/03/2018.
 */

public class CreateIngredientEntryRecipeActivity extends CreateIngredientEntryActivity<Recipe> {

    @Override
    public Recipe addEntryToType(IngredientEntry entry, Recipe recipe) {
        recipe.getIngredientEntries().add(entry);
        return recipe;
    }

    @Override
    public GenericRestCaller<Recipe> getRestCaller() {
        return new RecipeRestCaller();
    }

    @Override
    public IngredientEntry addTypeToEntry(IngredientEntry entry, Recipe recipe) {
        entry.setRecipe(recipe);
        return entry;
    }
}
