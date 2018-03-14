package com.example.abilambin.nutritio.utils;

/**
 * Created by Matthieu on 14/03/2018.
 */

public class Utils {

    public static int percent(float val, float obj){
        if (obj == 0) return Math.round(val*100);

        float result = (val/obj)*100;
        return Math.round(result);
    }
}
