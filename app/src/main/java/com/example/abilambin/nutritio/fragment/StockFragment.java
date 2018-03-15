package com.example.abilambin.nutritio.fragment;

import com.example.abilambin.nutritio.activity.AddIngredientToStockActivity;
import com.example.abilambin.nutritio.bdd.model.Person;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.specific.PersonRestCaller;
import com.example.abilambin.nutritio.restApi.specific.StockRestCaller;
import com.example.abilambin.nutritio.utils.NConstants;
import com.example.abilambin.nutritio.utils.Utils;

import java.util.concurrent.ExecutionException;

public class StockFragment extends IngredientListFragment<Stock> {

    @Override
    public Class getAddIngredientActivity() {
        return AddIngredientToStockActivity.class;


    }


    public StockFragment(){
        this.restCaller = new StockRestCaller();
    }

    @Override
    public String getTitle() {
        return "Stock";
    }

    @Override
    protected int getCurrentFragment() {
        return NConstants.STOCK_FRAGMENT;
    }

    @Override
    public int getListId() {
        PersonRestCaller restCaller = new PersonRestCaller();
        try {
            Person p = (Person) restCaller.get(Utils.getUserId(getActivity()));
            return p.getStock().getId();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (WebServiceCallException e) {
            e.printStackTrace();
        } catch (CannotAuthenticateUserException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
