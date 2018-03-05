package com.example.abilambin.nutritio.bdd.model.ingredientList;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Matthieu on 24/02/2018.
 */

public class Recipe extends IngredientList{
    private static long serialVersionUID = 1L;

    @DatabaseField
    private String image;

    public Recipe() {
    }

    public Recipe(int id, String name, String image) {
        this.setId(id);
        this.setName(name);
        this.image = image;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
