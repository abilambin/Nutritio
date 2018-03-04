package com.example.abilambin.nutritio.fragment;

import android.widget.AdapterView;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.restApi.specific.GrocerieRestCaller;

import butterknife.BindView;

public class GroceriesFragment extends IngredientListFragment<Grocerie> implements AdapterView.OnItemClickListener {

    @Override
    protected int getListLayout() {
        return R.layout.fragment_groceries;
    }

    public GroceriesFragment(){
        this.restCaller = new GrocerieRestCaller();
    }


}
