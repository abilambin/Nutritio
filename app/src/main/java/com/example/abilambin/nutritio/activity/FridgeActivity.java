package com.example.abilambin.nutritio.activity;

import android.content.Context;

import com.example.abilambin.nutritio.R;

public class FridgeActivity extends AbstractNavigationActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_fridge;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.fridge;
    }

    @Override
    public Context getCurrentContext() {
        return this;
    }
}
