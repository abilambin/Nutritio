package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by serial on 30/11/2017.
 */

public class Ingredient implements Serializable {

    private static long serialVersionUID = 2L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String brand;

    @DatabaseField
    private int energy;

    @DatabaseField
    private float proteins;

    @DatabaseField
    private float carbohydrates;

    @DatabaseField
    private float sugar;

    @DatabaseField
    private float fat;

    @DatabaseField
    private float saturatedFat;

    @DatabaseField
    private float fibres;

    @DatabaseField
    private float salt;

    @DatabaseField
    private float sodium;

    public Ingredient() {

    }

    public Ingredient(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public Ingredient(String name, String brand, int energy, float proteins, float carbohydrates, float sugar, float fat, float saturatedFat, float fibres, float salt, float sodium) {

        this.name = name;
        this.brand = brand;
        this.energy = energy;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.sugar = sugar;
        this.fat = fat;
        this.saturatedFat = saturatedFat;
        this.fibres = fibres;
        this.salt = salt;
        this.sodium = sodium;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
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

    public float getFibres() {
        return fibres;
    }

    public void setFibres(float fibres) {
        this.fibres = fibres;
    }

    public float getSalt() {
        return salt;
    }

    public void setSalt(float salt) {
        this.salt = salt;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }
}
