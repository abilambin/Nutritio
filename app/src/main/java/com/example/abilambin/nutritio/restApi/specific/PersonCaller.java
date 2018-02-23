package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.Person;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by bellamy on 23/02/18.
 */

public class PersonCaller extends GenericRestCaller {

    public PersonCaller() {
        super("people", new TypeToken<List<Person>>(){}, new TypeToken<Person>(){});
    }
}
