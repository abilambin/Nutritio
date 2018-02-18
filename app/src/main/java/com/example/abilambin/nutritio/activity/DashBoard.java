package com.example.abilambin.nutritio.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoard extends AbstractNavigationActivity {

    /**
     * juste pour l'exemple
     */
    //@BindView(R.id.button)
    //Button button;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);


    }

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
