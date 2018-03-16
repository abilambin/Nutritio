package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.Person;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class PersonRestCaller extends GenericRestCaller {

    public PersonRestCaller() {
        super("people", new TypeToken<List<Person>>(){}, new TypeToken<Person>(){});
    }
}
