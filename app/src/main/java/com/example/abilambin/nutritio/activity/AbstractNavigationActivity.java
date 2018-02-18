package com.example.abilambin.nutritio.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.fragment.AbstractListFragment;
import com.example.abilambin.nutritio.fragment.EnergyFragment;
import com.example.abilambin.nutritio.fragment.StockListFragment;

public abstract class AbstractNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

        navigationView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int itemId = item.getItemId();
                if (itemId == R.id.dashboard) {
                    startActivity(new Intent(getCurrentContext(), DashBoard.class));
                } else if (itemId == R.id.stocks) {

                    // Create a new fragment and specify the new fragment
                    //AbstractListFragment<Ingredient> fragment = new StockListFragment();
                    //Bundle args = new Bundle();
                    //args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
                    //fragment.setArguments(args);

                    // Insert the fragment by replacing any existing fragment
                    //FragmentManager fragmentManager = getFragmentManager();
                    //fragmentManager.beginTransaction()
                      //      .replace(R.id.container, fragment)
                        //    .commit();

                    android.app.Fragment fragment = new EnergyFragment();

                    // Insert the fragment by replacing any existing fragment
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .add(R.id.container, fragment)
                            .commit();

                    //startActivity(new Intent(getCurrentContext(), StockActivity.class));
                } else if (itemId == R.id.planning) {
                    startActivity(new Intent(getCurrentContext(), PlanningActivity.class));
                } else if(itemId == R.id.recipes){
                    startActivity(new Intent(getCurrentContext(), RecipesActivity.class));
                } else if(itemId == R.id.groceries){
                    startActivity(new Intent(getCurrentContext(), GroceriesActivity.class));
                }
                finish();
            }
        }, 300);

        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        Menu menu = navigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked) {
                item.setChecked(true);
                break;
            }
        }
    }

    public abstract int getContentViewId();

    public abstract int getNavigationMenuItemId();

    public abstract Context getCurrentContext();

}
