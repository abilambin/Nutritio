package com.example.abilambin.nutritio.utils;

import com.example.abilambin.nutritio.bdd.model.Goal;
import com.example.abilambin.nutritio.bdd.model.Meal;

import java.util.List;

/**
 * Created by Matthieu on 14/03/2018.
 */

public class PersonSession {
    private static PersonSession personSession;

    private Intakes globalIntake;
    private List<Meal> meals;
    private Goal goal;

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
    }

    public synchronized void invalidateIntakes(){
        this.globalIntake = null;
    }

    public synchronized  void invalidateMeals(){
        this.meals = null;
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
}
