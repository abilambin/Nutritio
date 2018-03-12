package com.example.abilambin.nutritio.fragment;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.AddIngredientToStockActivity;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.restApi.specific.StockRestCaller;

public class StockFragment extends IngredientListFragment<Stock> {

    @Override
    protected int getItem() {
        return 0;
    }

    @Override
    protected int getListLayout() {
        return R.layout.fragment_stock;
    }

    @Override
    public Class getAddIngredientActivity() {
        return AddIngredientToStockActivity.class;
    }


    public StockFragment(){
        this.restCaller = new StockRestCaller();
    }

}
