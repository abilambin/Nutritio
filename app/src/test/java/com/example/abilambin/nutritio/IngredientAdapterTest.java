package com.example.abilambin.nutritio;

import com.example.abilambin.nutritio.adapter.AddIngredientToListAdapter;
import com.example.abilambin.nutritio.bdd.model.Ingredient;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by serial on 16/03/2018.
 */

public class IngredientAdapterTest {



    @Test
    public void filterConditionTrueTest() {
        AddIngredientToListAdapter addIngredientToListAdapter = new AddIngredientToListAdapter();
        CharSequence sequence = new String("hello");
        Ingredient ingredient = new Ingredient("hello", "world");
        boolean condition = addIngredientToListAdapter.filterCondition(ingredient, sequence);
        assertTrue(condition);
    }

    @Test
    public void filterConditionFalseTest() {
        AddIngredientToListAdapter addIngredientToListAdapter = new AddIngredientToListAdapter();
        CharSequence sequence = new String("toto");
        Ingredient ingredient = new Ingredient("hello", "world");
        boolean condition = addIngredientToListAdapter.filterCondition(ingredient, sequence);
        assertFalse(condition);
    }
}
