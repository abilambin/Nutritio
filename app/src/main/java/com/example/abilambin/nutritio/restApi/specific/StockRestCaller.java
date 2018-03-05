package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by bellamy on 16/02/18.
 */

public class StockRestCaller extends GenericRestCaller {

    public StockRestCaller() {
        super("stocks", new TypeToken<List<Stock>>(){}, new TypeToken<Stock>(){});
    }
}
