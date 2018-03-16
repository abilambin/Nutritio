package com.example.abilambin.nutritio;


import com.example.abilambin.nutritio.utils.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UtilsTest{


    @Test
    public void percentTest(){
        float goal = 200;
        float done = 100;
        int res = (int) (done * 100 / goal);

        assertEquals(res, Utils.percent(done, goal));

        goal = 500;

        res = (int) (done * 100 / goal);

        assertEquals(res, Utils.percent(done, goal));
    }

}
