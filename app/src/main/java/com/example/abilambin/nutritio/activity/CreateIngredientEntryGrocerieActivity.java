package com.example.abilambin.nutritio.activity;

import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.GrocerieRestCaller;


public class CreateIngredientEntryGrocerieActivity extends CreateIngredientEntryActivity<Grocerie> {

    @Override
    public Grocerie addEntryToType(IngredientEntry entry, Grocerie grocerie) {
        grocerie.getIngredientEntries().add(entry);
        return grocerie;
    }

    @Override
    public GenericRestCaller<Grocerie> getRestCaller() {
        return new GrocerieRestCaller();
    }

    @Override
    public IngredientEntry addTypeToEntry(IngredientEntry entry, Grocerie grocerie) {
        entry.setGrocerie(grocerie);
        return entry;
    }
}
