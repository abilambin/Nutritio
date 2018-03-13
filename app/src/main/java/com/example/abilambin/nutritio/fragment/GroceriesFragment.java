package com.example.abilambin.nutritio.fragment;

import android.view.View;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.AddIngredientToGrocerieActivity;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.restApi.specific.GrocerieRestCaller;

import viewHolder.GenericViewHolder;
import viewHolder.IngredientEntryViewHolder;

public class GroceriesFragment extends IngredientListFragment<Grocerie> {

    @Override
    public Class getAddIngredientActivity() {
        return AddIngredientToGrocerieActivity.class;
    }

    public GroceriesFragment(){
        this.restCaller = new GrocerieRestCaller();
    }
}
