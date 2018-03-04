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
import java.util.Date;
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
            List<Meal> list = new ArrayList<>();
            return list;



    }

    @Override
    protected View createElementView(final Meal meal, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.list_meal, null);


        if (meal.getDate() != null) {
            Log.d("MealListFragment TEST :",meal.getDate().getHours()+"");
            TextView hourMealTV = vi.findViewById(R.id.mealItemHourTextView);
            Date mealDate = meal.getDate();
            int h = mealDate.getHours();
            String s = "";
            if(h < 10){
                s = "0";
            }
            s += mealDate.getHours()+"H";
            int m = mealDate.getMinutes();
            if(m < 10){
                s += "0";
            }
            s += m;
            hourMealTV.setText(s);
        }

        TextView nameMealTV = vi.findViewById(R.id.mealItemNameTextView);
        nameMealTV.setText(meal.getName());

        Bundle bundle = getArguments();
        if (bundle != null) {
            title.setText(bundle.getString("title"));
        }


        ll = vi.findViewById(R.id.linearLayout);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecipeActivity.class);

                intent.putExtra("name", meal.getName());
                intent.putExtra("description", meal.getRecipe());
                startActivity(intent);

            }
        });


        return vi;
    }

}
