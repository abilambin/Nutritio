package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by bellamy on 23/02/18.
 */

public class Goal {

    private static long serialVersionUID = 1L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private int energy;

    @DatabaseField
    private int protein;

    @DatabaseField
    private int carbohydrate;

    @DatabaseField
    private int sugar;

    @DatabaseField
    private int fat;

    @DatabaseField
    private int saturatedFat;

    @DatabaseField
    private int fibre;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Person person;

    public Goal(){ }

    public Goal(int id, int energy, int protein, int carbohydrate, int sugar, int fat, int saturatedFat, int fibre, Person person) {
        this.id = id;
        this.energy = energy;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.fat = fat;
        this.saturatedFat = saturatedFat;
        this.fibre = fibre;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", energy=" + energy +
                ", protein=" + protein +
                ", carbohydrate=" + carbohydrate +
                ", sugar=" + sugar +
                ", fat=" + fat +
                ", saturatedFat=" + saturatedFat +
                ", fibre=" + fibre +
                ", person=" + person +
                '}';
    }
}
