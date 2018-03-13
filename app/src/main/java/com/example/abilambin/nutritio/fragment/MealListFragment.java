package com.example.abilambin.nutritio.fragment;

import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.specific.MealRestCaller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import viewHolder.MealViewHolder;

public class MealListFragment extends AbstractListFragment<Meal> {

    @BindView(R.id.title)
    protected TextView title;

    MealRestCaller mealRestCaller = new MealRestCaller();

    MealViewHolder mealViewHolder;

    private int item = R.layout.item_meal;


    @Override
    protected int getItem() { return R.layout.item_meal; }

    @Override
    protected int getListLayout() {
        return R.layout.list_meal;
    }

    @Override
    public List<Meal> getList(){
        try {
            //On récupère la liste des ingrédients récupéré par appel rest
            List<Meal> list = (List<Meal>) mealRestCaller.getAll();

            //Si elle est null, alors on en crée une vide
            if (list == null) return new ArrayList<>();

            return list;

        } catch (WebServiceCallException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (InterruptedException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ExecutionException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (CannotAuthenticateUserException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        }

    }






}
