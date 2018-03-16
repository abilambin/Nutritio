package com.example.abilambin.nutritio.bdd.model;

import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Meal implements Serializable{

    private static long serialVersionUID = 3L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private Date date;

    @DatabaseField
    private ArrayList<Recipe> recipes;

    @DatabaseField
    private Person person;

    public Meal(){

    }

    public Meal(String name, Date date){
        this.name = name;
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

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipe(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }
}
