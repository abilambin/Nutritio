package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.AddIngredientToGrocerieActivity;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;

import java.util.List;

import butterknife.BindView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.abilambin.nutritio.activity.LoginActivity.APP_INFO_NAME;

public class RecipeIngredientListFragment extends AbstractListFragment<IngredientEntry>{

    private String typeName;

    @BindView(R.id.addIngredientButton)
    FloatingActionButton addIngredientButton;

    private Recipe recipe;
/*
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences prefs = getActivity().getSharedPreferences(APP_INFO_NAME, MODE_PRIVATE);
        final Integer listeId = prefs.getInt("listId", 2); //TODO

        typeName = (String) getArguments().get("typeName");
        View v = super.onCreateView(inflater, container, savedInstanceState);

        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), getAddIngredientActivity());
                intent.putExtra(typeName, listeId);
                intent.putExtra("typeName", typeName);
                startActivity(intent);
            }
        });

        return v;
    }
*/
    public Class getAddIngredientActivity() {
        return null;//AddIngredientToGrocerieActivity.class;
    }

    @Override
    public String getTitle() {
        return "Ingrédients";
    }

    @Override
    protected int getItem() {
        return R.layout.item_ingredient;
    }

    protected int getListLayout() {
        return R.layout.list_ingredient;
    }

    @Override
    protected List<IngredientEntry> getList() {
        Bundle bundle = getActivity().getIntent().getExtras();
        recipe = (Recipe) bundle.get("recipe");

        return removeIngredientWithoutQuantity(recipe.getIngredientEntries());
    }


}
