package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by serial on 30/11/2017.
 */

public class User implements Serializable {

    private static long serialVersionUID = 1L;

    @DatabaseField
    private String userName;

    @DatabaseField
    private String firstName;

    @DatabaseField
    private String lasteName;

    @DatabaseField
    private String email;

    @DatabaseField
    private String password;

    @DatabaseField
    private String birthDate;

    @DatabaseField
    private int poids;

    @DatabaseField
    private int taille;

    @DatabaseField
    private Stock stock;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Grocerie grocerie;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private List<Ingredient> favoriteIngredients;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private List<Meal> favoriteMeals;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private List<Ingredient> blacklist;

    public User() {

    }

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

    public String getAge() {
        return birthDate;
    }

    public void setAge(String birthDate) {
        this.birthDate = birthDate;
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

    public Grocerie getGrocerie() {
        return grocerie;
    }

    public void setGrocerie(Grocerie grocerie) {
        this.grocerie = grocerie;
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
