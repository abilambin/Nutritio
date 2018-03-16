package com.example.abilambin.nutritio.bdd.model;

import com.example.abilambin.nutritio.bdd.model.enums.Unit;
import com.example.abilambin.nutritio.bdd.model.ingredientList.BlackList;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;


public class IngredientEntry implements Serializable {

    private static long serialVersionUID = 6L;

    @DatabaseField(generatedId = true)
    private Integer id;

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

    public IngredientEntry() {

    }

    public IngredientEntry(Integer id, int amount, Unit unit, Ingredient ingredient, BlackList blackList, Grocerie grocerie, Recipe recipe, Stock stock, Meal meal) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Unit getUnit() { return unit; }

    public String getUnitText() {
        String result;
        switch (unit) {
            case GRAMM :
                result = "grammes";
                break;
            case MILLILITRE :
                result = "millilitres";
                break;
            case UNIT :
                result = "unités";
                break;
            case TEASPOON :
                result = "cuillère à café";
                break;
            case TABLESPOON :
                result = "cuillère à soupe";
                break;
            case CUP :
                result = "tasse";
                break;
            default:
                result = "unité";
                break;
        }

        return result;
    }

    public static Unit getUnitFromText(String text){
        String s = text.toLowerCase();
        switch (s){
            case "gramme":
            case "grammes":
            case "grammes (g)":
                return Unit.GRAMM;
            case "millilitres":
            case "millilitres (ml)":
                return Unit.MILLILITRE;
            case "unités":
                return Unit.UNIT;
            case "cuillère à café":
                return Unit.TEASPOON;
            case "cuillère à soupe" :
                return Unit.TABLESPOON;
            case "tasse" :
                return Unit.CUP;
            default:
                return null;
        }
    }

    public String getUnitSmallText() {
        String result;
        switch (unit) {
            case GRAMM :
                result = "g";
                break;
            case MILLILITRE :
                result = "ml";
                break;
            case UNIT :
                result = "unit.";
                break;
            case TEASPOON :
                result = " c.à.c.";
                break;
            case TABLESPOON :
                result = " c.à.s.";
                break;
            case CUP :
                result = "tasse";
                break;
            default:
                result = "unit.";
                break;
        }

        return result;
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
        return "IngredientEntry{" +
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
