package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by bellamy on 23/02/18.
 */

public class BlackList {

    private static long serialVersionUID = 5L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    public BlackList(){

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
}
