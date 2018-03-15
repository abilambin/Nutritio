package com.example.abilambin.nutritio.utils;

import com.example.abilambin.nutritio.bdd.model.Goal;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.bdd.model.Person;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.bdd.model.ingredientList.ScoredRecipe;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;

import java.util.List;


public class PersonSession {
    private static PersonSession personSession;

    private Intakes globalIntake;
    private List<Meal> meals;
    private Goal goal;
    private List<ScoredRecipe> recipe;
    private Person person;
    private Stock stock;
    private Grocerie grocerie;

    private PersonSession(){
        this.invalidateData();
    }

    public synchronized static PersonSession getInstance(){
        if(personSession != null){
           return personSession;
        }

        personSession = new PersonSession();

        return personSession;
    }

    // Invalidate method

    public synchronized void invalidateData(){
        this.globalIntake = null;
        this.meals = null;
        this.recipe = null;
        this.stock = null;
        this.grocerie = null;
    }

    public synchronized void invalidateIntakes(){
        this.globalIntake = null;
    }

    public synchronized void invalidateMeals(){
        this.meals = null;
    }

    public synchronized void invalidateRecipe() {
        this.recipe = null;
    }

    public synchronized void invalidateStock() {
        this.stock = null;
    }

    public synchronized  void invalidateGrocerie() {
        this.grocerie = null;
    }

    // Getter Setter

    public synchronized Intakes getGlobalIntake() {
        return globalIntake;
    }

    public synchronized void setGlobalIntake(Intakes globalIntake) {
        this.globalIntake = globalIntake;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public void setRecipe(List<ScoredRecipe> recipe) {
        this.recipe = recipe;
    }

    public List<ScoredRecipe> getRecipe() {
        return recipe;
    }

    public synchronized Person getPerson() {
        return person;
    }

    public synchronized void setPerson(Person person) {
        this.person = person;
    }

    public synchronized void setStock(Stock stock) {
        this.stock = stock;
    }

    public synchronized Stock getStock() {
        return this.stock;
    }

    public Grocerie getGrocerie() {
        return grocerie;
    }

    public void setGrocerie(Grocerie grocerie) {
        this.grocerie = grocerie;
    }
}
