package com.example.abilambin.nutritio;

import android.content.Context;

public class RecipesActivity extends AbstractNavigationActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_recipes;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.recipes;
    }

    @Override
    public Context getCurrentContext() {
        return this;
    }
}
