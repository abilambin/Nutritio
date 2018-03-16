package com.example.abilambin.nutritio.restApi.specific;


import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class RecipeRestCaller extends GenericRestCaller {

    public RecipeRestCaller() {
        super("recipes", new TypeToken<List<Recipe>>(){}, new TypeToken<Recipe>(){});
    }
}

