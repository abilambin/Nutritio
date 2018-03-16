package com.example.abilambin.nutritio;


import com.example.abilambin.nutritio.bdd.model.Person;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.bdd.model.ingredientList.ScoredRecipe;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.utils.Intakes;
import com.example.abilambin.nutritio.utils.PersonSession;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class PersonSessionTest {

    private static PersonSession personSession;
    private static Intakes intakes;
    private static Person person;
    private static Stock stock;
    private static List<ScoredRecipe> recipes;
    private static Grocerie grocerie;

    @BeforeClass
    public static void getPersonSessionInstance(){
        personSession = PersonSession.getInstance();
        intakes = new Intakes();
        person = new Person(0, null, null, null, null, null, null);
        stock = new Stock();
        recipes = new ArrayList<>();
        grocerie = new Grocerie();
    }

    @Test
    public void cacheIntakes(){
        personSession.setGlobalIntake(intakes);
        assertEquals(intakes, personSession.getGlobalIntake());
    }

    @Test
    public void cachePerson(){
        personSession.setPerson(this.person);
        assertEquals(this.person, personSession.getPerson());
    }

    @Test
    public void cacheStock(){
        personSession.setStock(this.stock);
        assertEquals(this.stock, personSession.getStock());
    }

    @Test
    public void cacheRecipes(){
        personSession.setRecipe(this.recipes);
        assertEquals(recipes, personSession.getRecipe());
    }

    @Test
    public void cacheGrocerie(){
        personSession.setGrocerie(this.grocerie);
        assertEquals(this.grocerie, personSession.getGrocerie());
    }

    @Test
    public void testInvalidation(){
        personSession.invalidateData();
        assertNull(personSession.getGlobalIntake());
        assertNull(personSession.getRecipe());
        assertNull(personSession.getStock());
        assertNull(personSession.getPerson());
        assertNull(personSession.getGrocerie());
    }
}
