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
    private float protein;

    @DatabaseField
    private float carbohydrate;

    @DatabaseField
    private float sugar;

    @DatabaseField
    private float fat;

    @DatabaseField
    private float saturatedFat;

    @DatabaseField
    private float fibre;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Person person;

    public Goal(){ }

    public Goal(int id, int energy, float protein, float carbohydrate, float sugar, float fat, float saturatedFat, float fibre, Person person) {
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

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public float getSugar() {
        return sugar;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(float saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public float getFibre() {
        return fibre;
    }

    public void setFibre(float fibre) {
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
