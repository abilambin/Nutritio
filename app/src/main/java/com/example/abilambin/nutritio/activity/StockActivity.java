package com.example.abilambin.nutritio.activity;

import android.content.Context;

import com.example.abilambin.nutritio.R;

public class StockActivity extends AbstractNavigationActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_stock;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.stocks;
    }

    @Override
    public Context getCurrentContext() {
        return this;
    }
}


