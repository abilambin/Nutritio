package com.example.abilambin.nutritio.bdd.model;

import com.example.abilambin.nutritio.bdd.model.enums.Category;
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
    private Category category;

    @DatabaseField
    private String brand;

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


    public Ingredient() {

    }

    public Ingredient(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public Ingredient(String name, String brand, int energy, float protein, float carbohydrate, float sugar, float fat, float saturatedFat, float fibre) {

        this.name = name;
        this.brand = brand;
        this.energy = energy;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.fat = fat;
        this.saturatedFat = saturatedFat;
        this.fibre = fibre;

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

    public Category getCategory() {
        return category;
    }

    public String getCategoryText() {
        String result;

        switch (category) {
            case FRUIT:
                result = "Fruits";
                break;
            case VEGETABLE_BEAN:
                result = "Légumes & Légumineuses";
                break;
            case MEAT_FISH:
                result = "Viandes et Poissons";
                break;
            case MILK_PRODUCT:
                result = "Produits Laitier";
                break;
            case DRINK:
                result = "Boissons";
                break;
            case GRAIN:
                result = "Féculents";
                break;
            case SWEET:
                result = "Patisseries & Friandises";
                break;
            case SAUCE_SPICE:
                result = "Epices & Sauces";
                break;
            case OTHER:
                result = "Autre";
                break;
            default:
                result = "Sans catégorie";
        }

        return result;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getEnergy() {
        return energy/1000;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public float getProtein() {
        return protein/1000;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarbohydrate() {
        return carbohydrate/1000;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public float getSugar() {
        return sugar/1000;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public float getFat() {
        return fat/1000;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getSaturatedFat() {
        return saturatedFat/1000;
    }

    public void setSaturatedFat(float saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public float getFibre() {
        return fibre/1000;
    }

    public void setFibre(float fibre) {
        this.fibre = fibre;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", energy=" + energy +
                ", protein=" + protein +
                ", carbohydrate=" + carbohydrate +
                ", sugar=" + sugar +
                ", fat=" + fat +
                ", saturatedFat=" + saturatedFat +
                ", fibre=" + fibre +
                '}';
    }
}
