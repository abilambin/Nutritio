package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by bellamy on 23/02/18.
 */

public class Goal {

    private static long serialVersionUID = 1L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private int energy;

    @DatabaseField
    private float protein;

    @DatabaseField
    private float carbohydrate;

    @DatabaseField
    private float sugar;

    @DatabaseField
    private float fat;

    @DatabaseField
    private float saturatedFat;

    @DatabaseField
    private float fibre;




}
