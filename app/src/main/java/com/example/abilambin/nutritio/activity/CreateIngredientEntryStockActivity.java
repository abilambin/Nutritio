package com.example.abilambin.nutritio.activity;

import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.StockRestCaller;


public class CreateIngredientEntryStockActivity extends CreateIngredientEntryActivity<Stock> {

    @Override
    public Stock addEntryToType(IngredientEntry entry, Stock type) {
        type.getIngredientEntries().add(entry);
        return type;
    }

    @Override
    public GenericRestCaller<Stock> getRestCaller() {
        return new StockRestCaller();
    }

    @Override
    public IngredientEntry addTypeToEntry(IngredientEntry entry, Stock type) {
        entry.setStock(type);
        return entry;
    }
}
