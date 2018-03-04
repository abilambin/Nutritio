package com.example.abilambin.nutritio.fragment;

import android.widget.AdapterView;
import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.restApi.specific.StockRestCaller;

public class StockFragment extends IngredientListFragment<Stock> implements AdapterView.OnItemClickListener {

    @Override
    protected int getListLayout() {
        return R.layout.fragment_stock;
    }


    public StockFragment(){
        this.restCaller = new StockRestCaller();
    }

}
