package com.example.abilambin.nutritio.activity;

import android.content.Context;
import android.widget.RelativeLayout;

import com.example.abilambin.nutritio.R;

public class DashBoard extends AbstractNavigationActivity {
    RelativeLayout layout1;
    RelativeLayout layout2;

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

    /* SI ON DECIDE DE RENDRE LES FRAGMENTS DYNAMIQUES
    private void loadFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EnergyFragment fragmentEnergy = new EnergyFragment();
        IntakesFragment fragmentApports = new IntakesFragment();

        fragmentTransaction.add(R.id.layout1, fragmentEnergy);
        fragmentTransaction.add(R.id.layout2, fragmentApports);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    */

}
