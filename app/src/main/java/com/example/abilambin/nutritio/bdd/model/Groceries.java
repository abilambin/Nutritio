package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by serial on 30/11/2017.
 */

public class Groceries implements Serializable {

    private static long serialVersionUID = 4L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private List<Ingredient> ingredients;

    public Groceries(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}