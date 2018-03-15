package com.example.abilambin.nutritio.fragment;

import com.example.abilambin.nutritio.activity.AddIngredientToGrocerieActivity;
import com.example.abilambin.nutritio.bdd.model.Person;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.specific.GrocerieRestCaller;
import com.example.abilambin.nutritio.restApi.specific.PersonRestCaller;
import com.example.abilambin.nutritio.utils.NConstants;
import com.example.abilambin.nutritio.utils.Utils;

import java.util.concurrent.ExecutionException;

public class GroceriesFragment extends IngredientListFragment<Grocerie> {

    @Override
    public Class getAddIngredientActivity() {
        return AddIngredientToGrocerieActivity.class;
    }

    public GroceriesFragment(){
        this.restCaller = new GrocerieRestCaller();
    }

    @Override
    public String getTitle() {
        return "Courses";
    }

    @Override
    protected int getCurrentFragment() {
        return NConstants.COURSES_FRAGMENT;
    }

    @Override
    public int getListId() {
        PersonRestCaller restCaller = new PersonRestCaller();
        try {
            Person p = (Person) restCaller.get(Utils.getUserId(getActivity()));
            return p.getGrocerie().getId();
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
