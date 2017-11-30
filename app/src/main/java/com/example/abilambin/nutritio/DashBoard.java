package com.example.abilambin.nutritio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.InjectView;

public class DashBoard extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout layout1;
    RelativeLayout layout2;

    private TextView mTextMessage;

    //on injecte la view
    //on ne peut pas injecter une view depuis une variable locale
    //toujours le faire ici
    @InjectView(R.id.navigation)
    private BottomNavigationView navigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.dashboard:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.planning:
                    mTextMessage.setText(R.string.title_planning);
                    return true;
                case R.id.stocks:
                    mTextMessage.setText(R.string.title_stocks);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //loadFragments();
    }


    @Override
    public void onClick(View view) {

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
