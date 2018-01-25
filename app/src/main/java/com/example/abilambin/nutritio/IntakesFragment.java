package com.example.abilambin.nutritio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntakesFragment extends Fragment {

    @BindView(R.id.proteinesProgressBar)
    ProgressBar proteinesProgressBar;

    @BindView(R.id.proteinesPctTextView)
    TextView proteinesPctTextView;


    @BindView(R.id.glucidesProgressBar)
    ProgressBar glucidesProgressBar;

    @BindView(R.id.glucidesPctTextView)
    TextView glucidesPctTextView;

    @BindView(R.id.lipidesProgressBar)
    ProgressBar lipidesProgressBar;

    @BindView(R.id.lipidesPctTextView)
    TextView lipidesPctTextView;

    @BindView(R.id.fibresProgressBar)
    ProgressBar fibresProgressBar;

    @BindView(R.id.fibresPctTextView)
    TextView fibresPctTextView;

    @BindView(R.id.selProgressBar)
    ProgressBar selProgressBar;

    @BindView(R.id.selPctTextView)
    TextView selPctTextView;



    public IntakesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intakes, container, false);
        ButterKnife.bind(this, view);

        proteinesProgressBar.setProgress(75);
        proteinesPctTextView.setText("75%");

        glucidesProgressBar.setProgress(85);
        glucidesPctTextView.setText("85%");

        lipidesProgressBar.setProgress(92);
        lipidesPctTextView.setText("92%");

        fibresProgressBar.setProgress(53);
        fibresPctTextView.setText("53%");

        selProgressBar.setProgress(138);
        selPctTextView.setText("138%");

        return view;
    }

}
