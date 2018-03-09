package com.example.abilambin.nutritio.fragment;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.AddIngredientToGrocerieActivity;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.restApi.specific.GrocerieRestCaller;

public class GroceriesFragment extends IngredientListFragment<Grocerie> {

    @Override
    protected int getListLayout() {
        return R.layout.fragment_groceries;
    }

    @Override
    public Class getAddIngredientActivity() {
        return AddIngredientToGrocerieActivity.class;
    }

    public GroceriesFragment(){
        this.restCaller = new GrocerieRestCaller();
    }
}
