package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

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
    private String description;

    @DatabaseField
    private int tempsPreparation;

    @DatabaseField
    private int tempsCuisson;

    public Meal(){

    }

    public Meal(String name, int tempsPreparation, int tempsCuisson){
        this.name = name;
        this.tempsPreparation = tempsPreparation;
        this.tempsCuisson = tempsCuisson;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTempsPreparation() {
        return tempsPreparation;
    }

    public void setTempsPreparation(int tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }

    public int getTempsCuisson() {
        return tempsCuisson;
    }

    public void setTempsCuisson(int tempsCuisson) {
        this.tempsCuisson = tempsCuisson;
    }
}
