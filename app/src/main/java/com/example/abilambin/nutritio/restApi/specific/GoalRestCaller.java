package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.Goal;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Matthieu on 24/02/2018.
 */

public class GoalRestCaller extends GenericRestCaller {

    public GoalRestCaller() {
        super("goals", new TypeToken<List<Goal>>(){}, new TypeToken<List<Goal>>(){});
    }
}
