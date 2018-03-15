package com.example.abilambin.nutritio.activity;

import android.content.Context;

import com.example.abilambin.nutritio.bdd.model.Ingredient;

import java.util.List;

import adapter.AddIngredientToListAdapter;

/**
 * Created by serial on 09/03/2018.
 */

public class AddIngredientToGrocerieActivity extends AddIngredientToListActivity {
    @Override
    public AddIngredientToListAdapter getAdapter(Context ctx, List<Ingredient> ingredientList) {
        return new AddIngredientToListAdapter(ctx, ingredientList, CreateIngredientEntryGrocerieActivity.class);
    }
}
