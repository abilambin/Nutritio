package com.example.abilambin.nutritio.bdd.model.ingredientList;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by abilambin on 04/03/2018.
 */

public class IngredientList implements Serializable {

    private static long serialVersionUID = 4L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private List<IngredientEntry> ingredientEntries;

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

    public List<IngredientEntry> getIngredientEntries() {
        return ingredientEntries;
    }

    public void setIngredientEntries(List<IngredientEntry> ingredientEntries) {
        this.ingredientEntries = ingredientEntries;
    }
}
