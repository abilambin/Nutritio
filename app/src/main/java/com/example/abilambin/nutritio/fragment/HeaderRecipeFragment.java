package com.example.abilambin.nutritio.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HeaderRecipeFragment extends Fragment {

    @BindView(R.id.headerRecipeFragmentTitle)
    TextView title;

    @BindView(R.id.headerRecipeFragmentPreparationBakingTime)
    TextView tempsPreparation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_header_recipe, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getActivity().getIntent().getExtras();
        Recipe recipe = (Recipe) bundle.get("recipe");

        title.setText(recipe.getName());
        tempsPreparation.setText("Préparation : 15 min. "
                + " Cuisson : 10 min.");

        return view;
    }

}
