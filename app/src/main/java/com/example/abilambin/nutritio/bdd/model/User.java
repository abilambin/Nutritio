package com.example.abilambin.nutritio.bdd.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by serial on 30/11/2017.
 */

public class User implements Serializable {
    private static long serialVersionUID = 1L;

    private String userName;
    private String firstName;
    private String lasteName;
    private String email;
    private String password;

    private int age, poids, taille;

    private Stock stock;

    private Groceries groceries;

    private List<Ingredient> favoriteIngredients;

    private List<Meal> favoriteMeals;

    private List<Ingredient> blacklist;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasteName() {
        return lasteName;
    }

    public void setLasteName(String lasteName) {
        this.lasteName = lasteName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Groceries getGroceries() {
        return groceries;
    }

    public void setGroceries(Groceries groceries) {
        this.groceries = groceries;
    }

    public List<Ingredient> getFavoriteIngredients() {
        return favoriteIngredients;
    }

    public void setFavoriteIngredients(List<Ingredient> favoriteIngredients) {
        this.favoriteIngredients = favoriteIngredients;
    }

    public List<Meal> getFavoriteMeals() {
        return favoriteMeals;
    }

    public void setFavoriteMeals(List<Meal> favoriteMeals) {
        this.favoriteMeals = favoriteMeals;
    }

    public List<Ingredient> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(List<Ingredient> blacklist) {
        this.blacklist = blacklist;
    }
}
