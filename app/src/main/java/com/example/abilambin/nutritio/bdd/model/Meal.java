package com.example.abilambin.nutritio.bdd.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by serial on 30/11/2017.
 */

class Meal implements Serializable{

    private static long serialVersionUID = 3L;

    private int id;

    private String name;

    private List<Ingredient> ingredients;



}
