package com.example.abilambin.nutritio.activity;

import android.content.Context;

import com.example.abilambin.nutritio.R;

public class DashBoard extends AbstractNavigationActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_dash_board;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.dashboard;
    }

    @Override
    public Context getCurrentContext() {
        return this;
    }

}
