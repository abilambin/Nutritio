package com.example.abilambin.nutritio.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnergyFragment extends Fragment {

    @BindView(R.id.energyFragmentCircularProgressBarId)
    CircularProgressBar circularProgressBar;

    @BindView(R.id.energyTextProgress)
    TextView energyProgressText;

    public EnergyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_energy, container, false);
        ButterKnife.bind(this, view);

        energyProgressText.setText(IntakesFragment.energy + "%");
        int animationDuration = 1500;
        circularProgressBar.setProgressWithAnimation(IntakesFragment.energy, animationDuration);

        return view;
    }

}
