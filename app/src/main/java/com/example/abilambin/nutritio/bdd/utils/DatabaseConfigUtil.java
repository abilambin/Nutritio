package com.example.abilambin.nutritio.bdd.utils;

import com.example.abilambin.nutritio.bdd.model.Groceries;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.bdd.model.Stock;
import com.example.abilambin.nutritio.bdd.model.User;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by serial on 22/12/2017.
 *
 *
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    /*
            ATTENTION, NE SOURTOUT PAS MODIFIER CETTE CLASSE
            NI SUPPRIMER LE DOSSIER res/raw A LA RACINE DU PROJET
     */

    private static final Class<?>[] classes = new Class[] {
            Groceries.class, Ingredient.class, Meal.class, Stock.class, User.class
    };

    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile("../../app/src/main/res/raw/ormlite_config.txt", classes);
    }
}
