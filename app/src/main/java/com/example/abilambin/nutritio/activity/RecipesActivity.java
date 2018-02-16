package com.example.abilambin.nutritio.activity;

import android.content.Context;

import com.example.abilambin.nutritio.R;

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
