package com.example.abilambin.nutritio.utils;

/**
 * Created by Matthieu on 14/03/2018.
 */

public class PersonSession {
    private static PersonSession personSession;

    private Intakes globalIntake;

    private PersonSession(){
        this.invalidateData();
    }

    public static PersonSession getInstance(){
        if(personSession != null){
           return personSession;
        }

        personSession = new PersonSession();

        return personSession;
    }

    public void invalidateData(){
        this.globalIntake = null;
    }

    public Intakes getGlobalIntake() {
        return globalIntake;
    }

    public void setGlobalIntake(Intakes globalIntake) {
        this.globalIntake = globalIntake;
    }
}
