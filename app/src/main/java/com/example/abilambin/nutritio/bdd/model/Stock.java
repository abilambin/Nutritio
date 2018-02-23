package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by serial on 30/11/2017.
 */

public class Stock implements Serializable{

    private static long serialVersionUID = 5L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    public Stock() {

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
