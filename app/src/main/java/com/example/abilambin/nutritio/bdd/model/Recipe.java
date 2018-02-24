package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Matthieu on 24/02/2018.
 */

public class Recipe {
    private static long serialVersionUID = 1L;

    @DatabaseField
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String image;

    public Recipe() {
    }

    public Recipe(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
