package com.example.abilambin.nutritio;

import android.content.Context;

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
