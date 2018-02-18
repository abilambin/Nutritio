package com.example.abilambin.nutritio.activity;

import android.content.Context;

import com.example.abilambin.nutritio.R;

public class GroceriesActivity extends AbstractNavigationActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_groceries;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.groceries;
    }

    @Override
    public Context getCurrentContext() {
        return this;
    }
}
