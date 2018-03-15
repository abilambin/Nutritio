package com.example.abilambin.nutritio.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.backgroundTask.IntakesLoader;
import com.example.abilambin.nutritio.bdd.model.Goal;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.utils.Intakes;
import com.example.abilambin.nutritio.utils.PersonalGoal;
import com.example.abilambin.nutritio.utils.Utils;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.example.abilambin.nutritio.activity.LoginActivity.APP_INFO_NAME;


public class IntakesFragment extends Fragment {

    private List<IngredientEntry> ingredientEntries;

    private static int quantity = 100;

    private int mode = 3;

    @BindView(R.id.intakesTitle)
    TextView title;

    @BindView(R.id.proteinesProgressBar)
    ProgressBar proteinesProgressBar;

    @BindView(R.id.proteinesPctTextView)
    TextView proteinesPctTextView;

    @BindView(R.id.glucidesProgressBar)
    ProgressBar glucidesProgressBar;

    @BindView(R.id.glucidesPctTextView)
    TextView glucidesPctTextView;

    @BindView(R.id.sucreProgressBar)
    ProgressBar sucreProgressBar;

    @BindView(R.id.lipidesProgressBar)
    ProgressBar lipidesProgressBar;

    @BindView(R.id.lipidesPctTextView)
    TextView lipidesPctTextView;

    @BindView(R.id.agsProgressBar)
    ProgressBar agsProgressBar;

    @BindView(R.id.fibresProgressBar)
    ProgressBar fibresProgressBar;

    @BindView(R.id.fibresPctTextView)
    TextView fibresPctTextView;

    private Goal goal;


    public List<IngredientEntry> getIngredientEntries() {
        return ingredientEntries;
    }

    public void setIngredientEntries(List<IngredientEntry> ingredientEntries) {
        this.ingredientEntries = ingredientEntries;
    }

    public IntakesFragment() {
        // Required empty public constructor
        this.goal = PersonalGoal.getInstance().getGoal();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intakes, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Récupération de l'id utilisateur
        SharedPreferences prefs = this.getActivity().getSharedPreferences(APP_INFO_NAME, MODE_PRIVATE);
        int userId = Integer.parseInt(prefs.getString("id", null));

        IntakesLoader loader = new IntakesLoader(
                this.getActivity(),
                proteinesProgressBar, proteinesPctTextView,
                glucidesProgressBar, glucidesPctTextView,
                sucreProgressBar,
                lipidesProgressBar, lipidesPctTextView,
                agsProgressBar,
                fibresProgressBar, fibresPctTextView);

            loader.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
