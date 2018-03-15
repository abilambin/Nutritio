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


    public Ingredient() {

    }

    public Ingredient(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public Ingredient(String name, String brand, int energy, int protein, int carbohydrate, int sugar, int fat, int saturatedFat, int fibre) {

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
