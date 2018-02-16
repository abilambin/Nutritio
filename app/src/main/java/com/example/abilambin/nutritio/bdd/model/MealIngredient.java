package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by serial on 11/01/2018.
 */

public class IngredientEntry implements Serializable {

    private static long serialVersionUID = 6L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Meal meal;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Ingredient ingredient;

    @DatabaseField
    private int quantity;

    @DatabaseField
    private String typeOfQuantity;

    public IngredientEntry() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTypeOfQuantity() {
        return typeOfQuantity;
    }

    public void setTypeOfQuantity(String typeOfQuantity) {
        this.typeOfQuantity = typeOfQuantity;
    }
}
