package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by serial on 30/11/2017.
 */

public class Meal implements Serializable{

    private static long serialVersionUID = 3L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private Date date;

    @DatabaseField
    private String description;

    @DatabaseField
    private int preparationTime;

    @DatabaseField
    private int bakingTime;

    public Meal(){

    }


    public Meal(String name, int preparationTime, int bakingTime){
        this.name = name;
        this.preparationTime = preparationTime;
        this.bakingTime = bakingTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getBakingTime() {
        return bakingTime;
    }

    public void setBakingTime(int bakingTime) {
        this.bakingTime = bakingTime;
    }
}
