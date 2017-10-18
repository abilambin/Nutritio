package com.example.abilambin.fragmenttuto;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import values.Fragment2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bn1;
    //Button bn2;
    RelativeLayout fr1;
    boolean status1 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bn1 = (Button) findViewById(R.id.bn1);
        bn1.setOnClickListener(this);

        //bn2 = (Button) findViewById(R.id.bn2);
        //bn2.setOnClickListener(this);

        fr1 = (RelativeLayout) findViewById(R.id.fragment_container1);
        fr1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();

        switch (view.getId()) {


            case R.id.bn1:

                fragmentManager.popBackStack();

                if(!status1) {
                    fragmentTransaction.add(R.id.fragment_container1, fragment1);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    bn1.setText("CHARGER AUTRE FRAGMENT");
                    status1 = true;
                } else {
                    fragmentTransaction.add(R.id.fragment_container1, fragment2);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    status1 = false;
                }
                break;

            /*

            case R.id.bn2:
                fragmentManager.popBackStack();

                fragmentTransaction.add(R.id.fragment_container1, fragment1);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.add(R.id.fragment_container2, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                bn1.setText("CHARGER UN SEUL FRAGMENT");


                    status1 = false;
                break;
            */

            /*

            case R.id.fragment_container1:

                fragmentManager.popBackStack();

                if(!status1) {
                    fragmentTransaction.add(R.id.fragment_container1, fragment1);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    status1 = true;
                } else {
                    fragmentTransaction.add(R.id.fragment_container1, fragment2);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    status1 = false;
                }
                break;
                */
        }
    }
}
