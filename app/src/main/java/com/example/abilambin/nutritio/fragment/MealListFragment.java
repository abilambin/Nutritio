package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.RecipeActivity;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealListFragment extends AbstractListFragment<Meal> implements AdapterView.OnItemClickListener {

    LinearLayout ll;

    @Override
    protected int getListLayout() {
        return R.layout.fragment_meal_list;
    }

    @Override
    public List<Meal> getList(){
        try {
            Dao<Meal, Integer> dao = getHelper().getMealsDao();
            List<Meal> list = dao.queryForAll();
            return list;
        } catch (SQLException e){
            System.out.println("##### ERROR - Impossible de récupérer les plats");
            return new ArrayList<>();
        }

    }

    @Override
    protected View createElementView(Meal meal, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.list_meal, null);
        String minutes = " min";

        TextView nameMealTV = vi.findViewById(R.id.mealItemNameTextView);
        nameMealTV.setText(meal.getName());

        TextView tempsCuissonTV = vi.findViewById(R.id.mealItemCuissonTimeTextView);
        tempsCuissonTV.setText(meal.getTempsCuisson()+minutes);

        TextView tempsPreparaionTV = vi.findViewById(R.id.mealItemPreparationTimeTextView);
        tempsPreparaionTV.setText(meal.getTempsPreparation()+minutes);
        return vi;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.list_meal, null);

        ll = view.findViewById(R.id.linearLayout);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
