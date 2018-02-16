package com.example.abilambin.nutritio.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class HeaderRecipeFragment extends Fragment {

    @BindView(R.id.headerRecipeFragmentTitle)
    TextView title;

    //@BindView(R.id.headerRecipeFragmentPreparationTime)
    TextView tempsPreparation;

    public HeaderRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_header_recipe, container, false);
        ButterKnife.bind(this, view);

        Intent intent = getActivity().getIntent();

        title.setText(intent.getStringExtra("title"));
        //tempsPreparation.setText(intent.getStringExtra("tempsPreparation"));

        return view;
    }

}
