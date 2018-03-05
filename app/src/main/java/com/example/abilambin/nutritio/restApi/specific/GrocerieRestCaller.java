package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by bellamy on 16/02/18.
 */

public class GrocerieRestCaller extends GenericRestCaller {

    public GrocerieRestCaller() {
        super("groceries", new TypeToken<List<Grocerie>>(){}, new TypeToken<Grocerie>(){});
    }
}
