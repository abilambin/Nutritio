package com.example.abilambin.nutritio;

import android.content.Context;

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
