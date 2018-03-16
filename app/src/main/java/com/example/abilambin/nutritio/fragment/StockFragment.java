package com.example.abilambin.nutritio.fragment;

import com.example.abilambin.nutritio.activity.AddIngredientToStockActivity;
import com.example.abilambin.nutritio.bdd.model.Person;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.specific.PersonRestCaller;
import com.example.abilambin.nutritio.restApi.specific.StockRestCaller;
import com.example.abilambin.nutritio.utils.NConstants;
import com.example.abilambin.nutritio.utils.PersonSession;
import com.example.abilambin.nutritio.utils.Utils;

import java.util.concurrent.ExecutionException;

public class StockFragment extends IngredientListFragment<Stock> {

    public StockFragment(){
        this.restCaller = new StockRestCaller();
    }

    @Override
    public Class getAddIngredientActivity() {
        return AddIngredientToStockActivity.class;
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
            PersonSession session = PersonSession.getInstance();

            // Récupération du cache
            if(session.getPerson() == null){
                Person p = (Person) restCaller.get(Utils.getUserId(getActivity()));
                return p.getStock().getId();
            }else{
                return session.getPerson().getStock().getId();
            }

        } catch (ExecutionException |WebServiceCallException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return -1;
    }

    public Stock getData() throws InterruptedException, ExecutionException, CannotAuthenticateUserException, WebServiceCallException {
        PersonSession session = PersonSession.getInstance();
        if(session.getStock() == null){
            Stock s = restCaller.get(getListId());
            session.setStock(s);
            return s;
        }else{
            return session.getStock();
        }

    }
}
