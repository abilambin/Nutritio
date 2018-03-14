package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.IngredientActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.IngredientList;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import viewHolder.GenericViewHolder;
import viewHolder.IngredientEntryViewHolder;

import static android.content.Context.MODE_PRIVATE;
import static com.example.abilambin.nutritio.activity.LoginActivity.APP_INFO_NAME;

public abstract class IngredientListFragment<T extends IngredientList> extends AbstractListFragment<IngredientEntry> {

    GenericRestCaller<T> restCaller;

    private String typeName;

    @BindView(R.id.addIngredientButton)
    FloatingActionButton addIngredientButton;

    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences prefs = getActivity().getSharedPreferences(APP_INFO_NAME, MODE_PRIVATE);
        final Integer listeId = prefs.getInt("listId", 2);

        typeName = (String) getArguments().get("typeName");
        View v = super.onCreateView(inflater, container, savedInstanceState);

        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), getAddIngredientActivity());
                intent.putExtra(typeName, listeId);
                intent.putExtra("typeName", typeName);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    protected int getListLayout() {
        return R.layout.list_ingredient;
    }

    @Override
    protected int getItem() { return R.layout.item_ingredient; }

    public abstract Class getAddIngredientActivity();

    @Override
    public List<IngredientEntry> getList(){
        try {
            //On récupère la liste des ingrédients récupéré par appel rest
            T list = restCaller.get(2);

            //Si elle est null, alors on en crée une vide
            if (list == null) return new ArrayList<>();


            //si la quantité est égale à 0, on affiche pas l'ingrédient
            List<IngredientEntry> entries = new ArrayList<>();
            if(list.getIngredientEntries() != null && !list.getIngredientEntries().isEmpty()) {
                for (IngredientEntry ingredientEntry : list.getIngredientEntries()) {
                    if(ingredientEntry.getAmount() != 0){
                        entries.add(ingredientEntry);
                    }
                }
            }

            return entries;

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
