package com.example.abilambin.nutritio.utils;

import com.example.abilambin.nutritio.bdd.model.Goal;


public class PersonalGoal {
    private static PersonalGoal personalGoal;

    private Goal goal;

    private final int proteineNeeds = 120;
    private final int glucideNeeds = 124;
    private final int lipideNeeds = 80;
    private final int sucreNeeds = 50;
    private final int fibreNeeds = 50;
    private final int agsNeeds = 20;
    private final int energy = 2500;

    public PersonalGoal(){
        this.goal = new Goal();
        this.goal.setProtein(proteineNeeds);
        this.goal.setCarbohydrate(glucideNeeds);
        this.goal.setFibre(fibreNeeds);
        this.goal.setFat(lipideNeeds);
        this.goal.setSugar(sucreNeeds);
        this.goal.setSaturatedFat(agsNeeds);
        this.goal.setEnergy(energy);
    }

    public static PersonalGoal getInstance(){
        if(personalGoal != null){
            return personalGoal;
        }

        personalGoal = new PersonalGoal();

        return personalGoal;
    }

    public Goal getGoal(){
        return this.goal;
    }

    public int getProteineNeeds() {
        return goal.getProtein();
    }

    public int getGlucideNeeds() {
        return goal.getCarbohydrate();
    }

    public int getLipideNeeds() {
        return goal.getFat();
    }

    public int getSucreNeeds() {
        return goal.getSugar();
    }

    public int getFibreNeeds() {
        return goal.getFibre();
    }

    public int getAgsNeeds() {
        return goal.getSaturatedFat();
    }

    public void setProteineNeeds(int protein) {
        this.goal.setProtein(protein);
    }

    public void setGlucideNeeds(int glucide) {
        this.goal.setCarbohydrate(glucide);
    }

    public void setLipideNeeds(int lipide) {
        this.goal.setFat(lipide);
    }

    public void setSucreNeeds(int sucre) {
        this.goal.setSugar(sucre);
    }

    public void setFibreNeeds(int fibre) {
        this.goal.setFibre(fibre);
    }

    public void setAgsNeeds(int ags) {
        this.goal.setSaturatedFat(ags);
    }
}
