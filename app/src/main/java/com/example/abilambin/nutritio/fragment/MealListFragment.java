package com.example.abilambin.nutritio.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.dao.DatabaseHelper;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealListFragment extends Fragment implements OnItemClickListener {

    private DatabaseHelper databaseHelper;

    @BindView(R.id.mealListFragmentList)
    LinearLayout list;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_list, container, false);
        ButterKnife.bind(this, view);

        List<Meal> meals = getMealList();
        for (int i=0; i < meals.size(); i++) {
            Meal meal = meals.get(i);
            View vi = inflater.inflate(R.layout.list_meal, null);

            TextView nameMealTV = vi.findViewById(R.id.mealItemNameTextView);
            nameMealTV.setText(meal.getName());

            TextView tempsCuissonTV = vi.findViewById(R.id.mealItemCuissonTimeTextView);
            tempsCuissonTV.setText(meal.getTempsCuisson()+"");

            TextView tempsPreparaionTV = vi.findViewById(R.id.mealItemPreparationTimeTextView);
            tempsPreparaionTV.setText(meal.getTempsPreparation()+"");

            list.addView(vi);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    private List<Meal> getMealList(){
        try {
            Dao<Meal, Integer> dao = getHelper().getMealsDao();
            List<Meal> list = dao.queryForAll();
            return list;
        } catch (SQLException e){
            System.out.println("##### ERROR - Impossible de récupérer les plats");
            return new ArrayList<>();
        }

    }


    private DatabaseHelper getHelper(){
        if(databaseHelper == null){
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }



}
