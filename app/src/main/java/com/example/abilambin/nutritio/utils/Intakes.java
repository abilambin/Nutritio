package com.example.abilambin.nutritio.utils;

import com.example.abilambin.nutritio.bdd.model.IngredientEntry;


public class Intakes {
    private int protein;
    private int carbohydrate;
    private int sugar;
    private int fat;
    private int saturatedFat;
    private int fibre;
    private int energy;

    public Intakes() {
        this.protein = 0;
        this.carbohydrate = 0;
        this.sugar = 0;
        this.fat = 0;
        this.saturatedFat = 0;
        this.fibre = 0;
        this.energy = 0;
    }

    public void addMoreIntakes(IngredientEntry entry) {
        addEnergy(entry);

        addProtein(entry);
        addCarbohydrate(entry);
        addFat(entry);
        addFibre(entry);
        addSaturatedFat(entry);
        addSugar(entry);

    }

    public void addProtein(IngredientEntry entry) {
        this.protein += entry.getIngredient().getProtein() * entry.getAmount() / 100;
    }

    public void addCarbohydrate(IngredientEntry entry) {
        this.carbohydrate += entry.getIngredient().getCarbohydrate() * entry.getAmount() / 100;
    }

    public void addSugar(IngredientEntry entry){
        this.sugar += entry.getIngredient().getSugar() * entry.getAmount() / 100;
    }

    public void addFat(IngredientEntry entry) {
        this.fat += entry.getIngredient().getFat() * entry.getAmount() / 100;
    }

    public void addSaturatedFat(IngredientEntry entry) {
        this.saturatedFat += entry.getIngredient().getSaturatedFat() * entry.getAmount() / 100;
    }

    public void addFibre(IngredientEntry entry) {
        this.fibre += entry.getIngredient().getFibre() * entry.getAmount() / 100;
    }

    public void addEnergy(IngredientEntry entry) {
        this.energy += entry.getIngredient().getEnergy() * entry.getAmount() / 100;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(int saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public int getFibre() {
        return fibre;
    }

    public void setFibre(int fibre) {
        this.fibre = fibre;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
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
