package com.example.abilambin.nutritio.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.PersonaliseIntakesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetGoalFragment extends Fragment {

    @BindView(R.id.personalise_intakes_button)
    Button personaliseIntakesButton;

    public SetGoalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_set_goal, container, false);
        ButterKnife.bind(this, v);

        personaliseIntakesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), PersonaliseIntakesActivity.class));
            }
        });


        return v;
    }

}
