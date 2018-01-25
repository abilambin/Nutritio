package com.example.abilambin.nutritio.activity;

import android.content.Context;

import com.example.abilambin.nutritio.R;

public class PlanningActivity extends AbstractNavigationActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_planning;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.planning;
    }

    @Override
    public Context getCurrentContext() {
        return this;
    }

}
