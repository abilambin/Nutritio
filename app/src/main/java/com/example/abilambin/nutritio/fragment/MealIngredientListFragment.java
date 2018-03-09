package com.example.abilambin.nutritio.fragment;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.restApi.specific.IngredientRestCaller;

public class MealIngredientListFragment extends IngredientListFragment<Grocerie> {

    @Override
    protected int getListLayout() {
        return R.layout.fragment_meal_ingredient_list;
    }

    @Override
    public Class getAddIngredientActivity() {
        return null;
    }

    public MealIngredientListFragment(){
        this.restCaller = new IngredientRestCaller();
    }
}
