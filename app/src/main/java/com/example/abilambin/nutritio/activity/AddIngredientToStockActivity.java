package com.example.abilambin.nutritio.activity;

import android.content.Context;

import com.example.abilambin.nutritio.adapter.AddIngredientToListAdapter;
import com.example.abilambin.nutritio.bdd.model.Ingredient;

import java.util.List;

/**
 * Created by serial on 09/03/2018.
 */

public class AddIngredientToStockActivity extends AddIngredientToListActivity {
    @Override
    public AddIngredientToListAdapter getAdapter(Context ctx, List<Ingredient> ingredients) {
        return new AddIngredientToListAdapter(ctx, ingredients, CreateIngredientEntryStockActivity.class);
    }
}
