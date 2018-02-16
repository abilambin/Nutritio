package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by bellamy on 16/02/18.
 */

public class IngredientRestCaller extends GenericRestCaller {

    public IngredientRestCaller() {
        super("ingredients", new TypeToken<List<Ingredient>>(){}, new TypeToken<Ingredient>(){});
    }
}
