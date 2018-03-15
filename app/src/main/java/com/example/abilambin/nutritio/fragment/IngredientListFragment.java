package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.AddIngredientToGrocerieActivity;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.IngredientList;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.abilambin.nutritio.activity.LoginActivity.APP_INFO_NAME;

public abstract class IngredientListFragment<T extends IngredientList> extends AbstractListFragment<IngredientEntry> {

    GenericRestCaller<T> restCaller;

    private String typeName;

    @BindView(R.id.addIngredientButton)
    FloatingActionButton addIngredientButton;

    @Override
    public String getTitle() {
        return "Ingrédients";
    }

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

    public abstract int getListId();
    public abstract T getData() throws InterruptedException, ExecutionException, CannotAuthenticateUserException, WebServiceCallException;

    @Override
    protected int getListLayout() {
        return R.layout.list_ingredient;
    }

    @Override
    protected int getItem() { return R.layout.item_ingredient; }

    @Override
    protected int getCurrentFragment() {
        return -1;
    }

    public Class getAddIngredientActivity() {
        return AddIngredientToGrocerieActivity.class;
    }

    @Override
    public List<IngredientEntry> getList(){
        //On récupère la liste des ingrédients récupéré par appel rest
        T list = null;
        try {
            list = getData();
        } catch (InterruptedException | ExecutionException | CannotAuthenticateUserException | WebServiceCallException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        List<IngredientEntry> entries = new ArrayList<>();
        if(list.getIngredientEntries() != null && !list.getIngredientEntries().isEmpty()) {
            for (IngredientEntry ingredientEntry : list.getIngredientEntries()) {
                entries.add(ingredientEntry);
            }
        }

        return entries;



    }

}
