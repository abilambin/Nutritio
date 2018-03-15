package com.example.abilambin.nutritio.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.example.abilambin.nutritio.activity.LoginActivity.APP_INFO_NAME;


public class Utils {

    /**
     * Renvoi un porcentage
     * @param val valeur a évaluer
     * @param obj valeur équivalente à 100%
     * @return
     */
    public static int percent(float val, float obj){
        if (((int) obj ) == 0) return Math.round(val*100);

        float result = (val/obj)*100;
        return Math.round(result);
    }

    /**
     * Renvoie l'id utilisateur
     * @param activity l'activité à partir de laquelle les préférences seront récupérées
     * @return
     */
    public static int getUserId(Activity activity){
        SharedPreferences prefs = activity.getSharedPreferences(APP_INFO_NAME, MODE_PRIVATE);
        return Integer.parseInt(prefs.getString("id", null));
    }
}
