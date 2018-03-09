package com.example.abilambin.nutritio.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.restApi.specific.GrocerieRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientRestCaller;

import butterknife.BindView;

public class MealIngredientListFragment extends IngredientListFragment<Grocerie> {

    @Override
    protected int getListLayout() {
        return R.layout.fragment_meal_ingredient_list;
    }

    public MealIngredientListFragment(){
        this.restCaller = new IngredientRestCaller();
    }
}
