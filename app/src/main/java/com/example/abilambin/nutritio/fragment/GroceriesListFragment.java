package com.example.abilambin.nutritio.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GroceriesListFragment extends AbstractListFragment<Ingredient> {

    @Override
    protected int getListLayout() {
        return R.layout.fragment_groceries_list;
    }

    @Override
    protected List<Ingredient> getList() {
        try {
            Dao<Ingredient, Integer> dao = getHelper().getIngredientsDao();
            return dao.queryForAll();
        } catch (SQLException e){
            System.out.println("##### ERROR - Impossible de récupérer le stock d'ingrédient");
            return new ArrayList<>();
        }
    }

    @Override
    protected View createElementView(Ingredient ingredient, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.list_groceries, null);

        TextView name = vi.findViewById(R.id.list_groceries_name);
        name.setText(ingredient.getName());

        return vi;
    }
}
