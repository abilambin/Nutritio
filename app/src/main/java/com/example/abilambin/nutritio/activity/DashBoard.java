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

import com.example.abilambin.nutritio.fragment.GroceriesFragment;
import com.example.abilambin.nutritio.fragment.IntakesFragment;
import com.example.abilambin.nutritio.fragment.MealListFragment;
import com.example.abilambin.nutritio.fragment.StockFragment;

public class DashBoard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    protected int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //On souhaite que la page qui s'affiche en premier soit celle du Dashboard
        id = R.id.dashboard;

        ButterKnife.bind(this);
        setContentView(R.layout.activity_dash_board);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

    }


    /**
     * Ajoute le fragment en paramètre à la vue en cours (à la suite des autres fragments déjà présents)
     * @param fragment le fragment à ajouter
     */
    private void addFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.container,fragment)
                .addToBackStack("frag")
                .commit();
    }

    /**
     * Permet de retirer tous les fragments de la vue (afin de les remplacer par de nouveaux par exemple)
     */
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

        //Au (re)démarrage de l'activité, on affiche la dernière "page" affichée.
        selectBottomNavigationBarItem(id);
        updateFragments(navigationView.getSelectedItemId());


    }

    /**
     * A la sélection d'un item dans la barre de navigation (en bas) :
     * on appelle les fragments voulus en fonction de la "page" désirée
     * @param item l'item cliqué dans le menu
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        updateFragments(item.getItemId());
        return true;
    }

    /**
     * Permet d'ajouter tous les fragments nécessaires pour une "page" en particulier
     * @param itemId l'id de la page voulue
     */
    private void updateFragments(Integer itemId) {
        clearStack();

        Bundle bundle;

        if (itemId == R.id.dashboard) {
            IntakesFragment intakesFragment = new IntakesFragment();
            intakesFragment.setMode(3);

            addFragment(new EnergyFragment());
            addFragment(intakesFragment);
            addFragment(new MealListFragment());

        } else if (itemId == R.id.stocks) {

            Fragment fragment = new StockFragment();
            addFragment(fragment);

        } else if (itemId == R.id.planning) {

            Fragment fragment = new MealListFragment();
            addFragment(fragment);


        } else if(itemId == R.id.recipes){

            bundle = new Bundle();
            bundle.putString("title", "Recettes");

            Fragment fragment = new MealListFragment();
            fragment.setArguments(bundle);
            addFragment(fragment);

        } else if(itemId == R.id.groceries){

            Fragment fragment = new GroceriesFragment();
            addFragment(fragment);

        }

        id = itemId;
    }

    /**
     * Permet de mettre en surbrillance l'item voulu dans la barre de navigation (celui sur lequel on aura cliqué)
     * @param itemId l'id de l'item à mettre en surbrillance
     */
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
