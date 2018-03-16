package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class GrocerieRestCaller extends GenericRestCaller {

    public GrocerieRestCaller() {
        super("groceries", new TypeToken<List<Grocerie>>(){}, new TypeToken<Grocerie>(){});
    }
}
