package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

import butterknife.BindView;

public class MealListFragment extends AbstractListFragment<Meal> implements AdapterView.OnItemClickListener {

    LinearLayout ll;



    @BindView(R.id.mealListFragmentTitle)
    protected TextView title;

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
    protected View createElementView(final Meal meal, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.list_meal, null);
        String minutes = " min";




        if (meal.getDate() != null) {
            Log.d("MealListFragment TEST :",meal.getDate().getHours()+"");
            TextView hourMealTV = vi.findViewById(R.id.mealItemHourTextView);
            String test = meal.getDate().getHours()+"";
            hourMealTV.setText("Heure :" + test);
        }

        TextView nameMealTV = vi.findViewById(R.id.mealItemNameTextView);
        nameMealTV.setText(meal.getName());

        TextView tempsCuissonTV = vi.findViewById(R.id.mealItemCuissonTimeTextView);
        tempsCuissonTV.setText(meal.getTempsCuisson()+minutes);

        TextView tempsPreparaionTV = vi.findViewById(R.id.mealItemPreparationTimeTextView);
        tempsPreparaionTV.setText(meal.getTempsPreparation()+minutes);

        Bundle bundle = getArguments();
        if (bundle != null) {
            title.setText(bundle.getString("title"));
        }


        ll = vi.findViewById(R.id.linearLayout);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecipeActivity.class);

                intent.putExtra("title", meal.getName());
                intent.putExtra("description", meal.getDescription());
                intent.putExtra("tempsPreparation", meal.getTempsPreparation());
                intent.putExtra("tempsCuisson", meal.getTempsCuisson());
                startActivity(intent);

            }
        });


        return vi;
    }

}
