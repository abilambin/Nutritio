package com.example.abilambin.nutritio;


import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.utils.Intakes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestIntakes {

    private Intakes intakes;

    private int protein;
    private int carbohydrate;
    private int sugar;
    private int fat;
    private int saturatedFat;
    private int fibre;
    private int energy;

    private IngredientEntry entry;

    @Before
    public void init(){
        this.intakes = new Intakes();
        this.entry = new IngredientEntry();
        Ingredient ingredient = new Ingredient();

        this.protein = 1;
        this.carbohydrate = 2;
        this.sugar = 3;
        this.fat = 4;
        this.saturatedFat = 5;
        this.fibre = 6;
        this.energy = 7;

        ingredient.setProtein(this.protein);
        ingredient.setCarbohydrate(this.carbohydrate);
        ingredient.setSugar(this.sugar);
        ingredient.setFat(this.fat);
        ingredient.setSaturatedFat(this.saturatedFat);
        ingredient.setEnergy(this.energy);
        ingredient.setFibre(this.fibre);

        this.entry.setIngredient(ingredient);
        this.entry.setAmount(100);
    }

    @Test
    public void testAddMoreIntakes(){
        intakes.addMoreIntakes(this.entry);

        assertEquals(this.protein, this.intakes.getProtein());
        assertEquals(this.carbohydrate, this.intakes.getCarbohydrate());
        assertEquals(this.sugar, this.intakes.getSugar());
        assertEquals(this.fat, this.intakes.getFat());
        assertEquals(this.saturatedFat, this.intakes.getSaturatedFat());
        assertEquals(this.fibre, this.intakes.getFibre());
        assertEquals(this.energy, this.intakes.getEnergy());

        this.intakes.addMoreIntakes(this.entry);

        assertEquals(2 * this.protein, this.intakes.getProtein());
        assertEquals(2 * this.carbohydrate, this.intakes.getCarbohydrate());
        assertEquals(2 * this.sugar, this.intakes.getSugar());
        assertEquals(2 * this.fat, this.intakes.getFat());
        assertEquals(2 * this.saturatedFat, this.intakes.getSaturatedFat());
        assertEquals(2 * this.fibre, this.intakes.getFibre());
        assertEquals(2 * this.energy, this.intakes.getEnergy());
    }

}
