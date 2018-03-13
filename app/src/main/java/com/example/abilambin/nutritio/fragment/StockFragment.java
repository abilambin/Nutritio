package com.example.abilambin.nutritio.fragment;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.AddIngredientToStockActivity;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.restApi.specific.StockRestCaller;

public class StockFragment extends IngredientListFragment<Stock> {

    @Override
    public Class getAddIngredientActivity() {
        return AddIngredientToStockActivity.class;
    }


    public StockFragment(){
        this.restCaller = new StockRestCaller();
    }

}
