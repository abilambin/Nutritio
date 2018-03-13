package com.example.abilambin.nutritio.fragment;

import android.view.View;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.AddIngredientToStockActivity;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.restApi.specific.StockRestCaller;

import viewHolder.GenericViewHolder;

public class StockFragment extends IngredientListFragment<Stock> {

    @Override
    public Class getAddIngredientActivity() {
        return AddIngredientToStockActivity.class;
    }


    public StockFragment(){
        this.restCaller = new StockRestCaller();
    }

}
