package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class StockRestCaller extends GenericRestCaller {

    public StockRestCaller() {
        super("stocks", new TypeToken<List<Stock>>(){}, new TypeToken<Stock>(){});
    }
}
