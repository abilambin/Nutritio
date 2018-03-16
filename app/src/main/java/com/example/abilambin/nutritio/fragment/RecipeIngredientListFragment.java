package com.example.abilambin.nutritio.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;

import java.util.List;

import butterknife.BindView;

public class RecipeIngredientListFragment extends AbstractListFragment<IngredientEntry>{

    @BindView(R.id.addIngredientButton)
    FloatingActionButton addIngredientButton;

    private Recipe recipe;

    @Override
    public String getTitle() {
        return "Ingrédients";
    }

    @Override
    protected int getItem() {
        return R.layout.item_ingredient;
    }

    @Override
    protected int getCurrentFragment() {
        return -1;
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
