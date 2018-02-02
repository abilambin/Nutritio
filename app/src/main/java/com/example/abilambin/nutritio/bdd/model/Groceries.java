package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by serial on 30/11/2017.
 */

public class Groceries implements Serializable {

    private static long serialVersionUID = 4L;

    @DatabaseField(generatedId = true)
    private int id;


    public Groceries(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
