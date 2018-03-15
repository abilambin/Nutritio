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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.example.abilambin.nutritio.activity.LoginActivity.APP_INFO_NAME;


public class IntakesFragment extends Fragment {

    private List<IngredientEntry> ingredientEntries;

    private static int quantity = 100;

    private int mode = 3;

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

        if(mode == 3){
            loader.execute(3);      // 3 i.e. Mode tous les repas
        }
    }

    private void setIntakes(Intakes intakes){
        proteinesProgressBar.setProgress(Utils.percent(intakes.getProtein() / 100, goal.getProtein()));
        proteinesPctTextView.setText(intakes.getProtein()  / 100+" / "+goal.getProtein());

        glucidesProgressBar.setProgress(Utils.percent(intakes.getCarbohydrate()  / 100, 100));
        glucidesPctTextView.setText(intakes.getCarbohydrate()  / 100+" / "+goal.getCarbohydrate());

        sucreProgressBar.setProgress(Utils.percent(intakes.getSugar()  / 100, 100));

        lipidesProgressBar.setProgress(Utils.percent(intakes.getFat()  / 100, 100));
        lipidesPctTextView.setText(intakes.getFat()  / 100+" / "+goal.getFat());

        agsProgressBar.setProgress(Utils.percent(intakes.getSaturatedFat()  / 100, 100));

        fibresProgressBar.setProgress(Utils.percent(intakes.getFibre()  / 100, 100));
        fibresPctTextView.setText(intakes.getFibre()  / 100+" / "+goal.getFibre());
    }

    public void generateIntakes() {
        if (ingredientEntries != null  && !ingredientEntries.isEmpty()){
            setIntakes(ingredientEntries);
        }
    }

    private void setIntakes(List<IngredientEntry> ingredientEntries) {

        float prot=0;
        float carb=0;
        float sugar=0;
        float fat=0;
        float sf=0;
        float fibre = 0;


        Ingredient ingredient;
        for (IngredientEntry entry : ingredientEntries) {
            ingredient = entry.getIngredient();

            prot += ingredient.getProtein() * entry.getAmount();
            carb += ingredient.getCarbohydrate() * entry.getAmount();
            sugar += ingredient.getSugar() * entry.getAmount();
            fat += ingredient.getFat() * entry.getAmount();
            sf += ingredient.getSaturatedFat() * entry.getAmount();
            fibre += ingredient.getFibre() * entry.getAmount();
        }

        proteinesProgressBar.setProgress(Utils.percent(prot, goal.getProtein()));
        proteinesPctTextView.setText(prot+" / "+goal.getProtein());

        glucidesProgressBar.setProgress(Utils.percent(carb, 100));
        glucidesPctTextView.setText(""+carb+" / "+goal.getCarbohydrate());

        sucreProgressBar.setProgress(Utils.percent(sugar, 100));

        lipidesProgressBar.setProgress(Utils.percent(fat, 100));
        lipidesPctTextView.setText(""+fat+" / "+goal.getFat());

        agsProgressBar.setProgress(Utils.percent(sf, 100));

        fibresProgressBar.setProgress(Utils.percent(fibre, 100));
        fibresPctTextView.setText(""+fibre+" / "+goal.getFibre());

        //PROTEINES
        showValueBar(proteinesProgressBar, proteinesPctTextView, prot, goal.getProtein());

        //GLUCIDES
        showValueBar(glucidesProgressBar, glucidesPctTextView, carb, goal.getCarbohydrate());

        //SUCRE
        showValueBar(sucreProgressBar, null, sugar, goal.getSugar());

        //LIPIDES
        showValueBar(lipidesProgressBar, lipidesPctTextView, fat, goal.getFat());

        //AGS
        showValueBar(agsProgressBar, null, sf, goal.getSaturatedFat());

        //FIBRES
        showValueBar(fibresProgressBar, fibresPctTextView, fibre, goal.getFibre());

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void showValueBar(ProgressBar bar, TextView view, float val, float obj) {
        int value = valueCalcul(val);
        bar.setProgress(Utils.percent(value, obj));
        if (view != null) view.setText(value + " / "+obj);
    }

    private static int valueCalcul(float val){
        float result = val*quantity/100;
        return Math.round(result);
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
