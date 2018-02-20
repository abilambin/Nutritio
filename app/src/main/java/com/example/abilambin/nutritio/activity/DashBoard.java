package com.example.abilambin.nutritio.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.fragment.EnergyFragment;

import butterknife.ButterKnife;

import com.example.abilambin.nutritio.fragment.IngredientListFragment;
import com.example.abilambin.nutritio.fragment.IntakesFragment;
import com.example.abilambin.nutritio.fragment.MealListFragment;

public class DashBoard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        setContentView(R.layout.activity_dash_board);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

    }

    private void addFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.container,fragment)
                .addToBackStack("frag")
                .commit();
    }

    private void clearStack() {
        int backStackEntry = getFragmentManager().getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                getFragmentManager().popBackStackImmediate();
            }
        }

        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
                android.support.v4.app.Fragment mFragment = getSupportFragmentManager().getFragments().get(i);
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        selectBottomNavigationBarItem(R.id.dashboard);
        updateFragments(navigationView.getSelectedItemId());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        updateFragments(item.getItemId());
        return true;
    }

    private void updateFragments(Integer itemId) {
        clearStack();

        Bundle bundle;

        if (itemId == R.id.dashboard) {

            addFragment(new EnergyFragment());
            addFragment(new IntakesFragment());
            addFragment(new MealListFragment());

        } else if (itemId == R.id.stocks) {

            bundle = new Bundle();
            bundle.putString("title", "Courses");

            Fragment fragment = new IngredientListFragment();
            fragment.setArguments(bundle);
            addFragment(fragment);

        } else if (itemId == R.id.planning) {

            bundle = new Bundle();
            bundle.putString("title", "Planning");

            Fragment fragment = new MealListFragment();
            fragment.setArguments(bundle);
            addFragment(fragment);


        } else if(itemId == R.id.recipes){

            bundle = new Bundle();
            bundle.putString("title", "Recettes");

            Fragment fragment = new MealListFragment();
            fragment.setArguments(bundle);
            addFragment(fragment);

        } else if(itemId == R.id.groceries){

            bundle = new Bundle();
            bundle.putString("title", "Courses");

            Fragment fragment = new IngredientListFragment();
            fragment.setArguments(bundle);
            addFragment(fragment);

        }
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



}
