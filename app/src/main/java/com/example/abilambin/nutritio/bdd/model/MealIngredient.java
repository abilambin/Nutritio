package com.example.abilambin.nutritio.bdd.model;

import com.example.abilambin.nutritio.bdd.model.enums.Unit;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by serial on 11/01/2018.
 */

public class MealIngredient implements Serializable {

    private static long serialVersionUID = 6L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private int amount;

    @DatabaseField(canBeNull = false)
    private Unit unit;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Ingredient ingredient;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private BlackList blackList;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Grocerie grocerie;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Recipe recipe;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Stock stock;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Meal meal;

    public MealIngredient() {

    }

    public MealIngredient(int id, int amount, Unit unit, Ingredient ingredient, BlackList blackList, Grocerie grocerie, Recipe recipe, Stock stock, Meal meal) {
        this.id = id;
        this.amount = amount;
        this.unit = unit;
        this.ingredient = ingredient;
        this.blackList = blackList;
        this.grocerie = grocerie;
        this.recipe = recipe;
        this.stock = stock;
        this.meal = meal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public BlackList getBlackList() {
        return blackList;
    }

    public void setBlackList(BlackList blackList) {
        this.blackList = blackList;
    }

    public Grocerie getGrocerie() {
        return grocerie;
    }

    public void setGrocerie(Grocerie grocerie) {
        this.grocerie = grocerie;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String toString() {
        return "MealIngredient{" +
                "id=" + id +
                ", amount=" + amount +
                ", unit=" + unit +
                ", ingredient=" + ingredient +
                ", blackList=" + blackList +
                ", grocerie=" + grocerie +
                ", recipe=" + recipe +
                ", stock=" + stock +
                ", meal=" + meal +
                '}';
    }
}
