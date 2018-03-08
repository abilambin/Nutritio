package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by abi on 08/03/2018.
 */

public class MealRestCaller extends GenericRestCaller {

    public MealRestCaller() {
        super("meals", new TypeToken<List<Meal>>(){}, new TypeToken<Meal>(){});
    }
}
