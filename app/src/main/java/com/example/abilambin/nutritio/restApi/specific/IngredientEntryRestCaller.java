package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Matthieu on 24/02/2018.
 */

public class IngredientEntryRestCaller extends GenericRestCaller {

    public IngredientEntryRestCaller() {
        super("ingredient-entries", new TypeToken<List<IngredientEntry>>(){}, new TypeToken<IngredientEntry>(){});
    }
}
