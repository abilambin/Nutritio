package com.example.abilambin.nutritio.utils;

import com.example.abilambin.nutritio.bdd.model.IngredientEntry;

/**
 * Created by Matthieu on 12/03/2018.
 */

public class Intakes {
    private long protein;
    private long carbohydrate;
    private long sugar;
    private long fat;
    private long saturatedFat;
    private long fibre;
    private long energy;

    public Intakes() {
        this.protein = 0;
        this.carbohydrate = 0;
        this.sugar = 0;
        this.fat = 0;
        this.saturatedFat = 0;
        this.fibre = 0;
        this.energy = 0;
    }

    public void addProtein(IngredientEntry entry) {
        this.protein += entry.getIngredient().getProtein() * entry.getAmount();
    }

    public void addCarbohydrate(IngredientEntry entry) {
        this.carbohydrate += entry.getIngredient().getProtein() * entry.getAmount();
    }

    public void addSugar(IngredientEntry entry){
        this.sugar += entry.getIngredient().getProtein() * entry.getAmount();
    }

    public void addFat(IngredientEntry entry) {
        this.fat += entry.getIngredient().getProtein() * entry.getAmount();
    }

    public void addSaturatedFat(IngredientEntry entry) {
        this.saturatedFat += entry.getIngredient().getProtein() * entry.getAmount();
    }

    public void addFibre(IngredientEntry entry) {
        this.fibre += entry.getIngredient().getProtein() * entry.getAmount();
    }

    public void addEnergy(IngredientEntry entry) {
        this.energy += entry.getIngredient().getProtein() * entry.getAmount();
    }

    public long getProtein() {
        return protein;
    }

    public void setProtein(long protein) {
        this.protein = protein;
    }

    public long getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(long carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public long getSugar() {
        return sugar;
    }

    public void setSugar(long sugar) {
        this.sugar = sugar;
    }

    public long getFat() {
        return fat;
    }

    public void setFat(long fat) {
        this.fat = fat;
    }

    public long getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(long saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public long getFibre() {
        return fibre;
    }

    public void setFibre(long fibre) {
        this.fibre = fibre;
    }

    public long getEnergy() {
        return energy;
    }

    public void setEnergy(long energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "Intake{" +
                "protein=" + protein +
                ", carbohydrate=" + carbohydrate +
                ", sugar=" + sugar +
                ", fat=" + fat +
                ", saturatedFat=" + saturatedFat +
                ", fibre=" + fibre +
                ", energy=" + energy +
                '}';
    }
}
