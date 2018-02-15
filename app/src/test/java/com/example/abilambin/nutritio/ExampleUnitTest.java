package com.example.abilambin.nutritio;

import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.restApi.RestCaller;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void fakeRestCallerTest() {
        RestCaller<Ingredient> restCaller = new RestCaller<>("ingredients");
        List<Ingredient> ingredients = restCaller.getAll();

        assertTrue(ingredients.size() > 0);

        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName());
        }
    }
}