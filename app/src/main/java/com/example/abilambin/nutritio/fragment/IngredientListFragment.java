package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.RecipeActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class IngredientListFragment extends AbstractListFragment<Ingredient> implements AdapterView.OnItemClickListener {

    LinearLayout ll;

    @BindView(R.id.ingredientListFragmentTitle)
    protected TextView title;



    @Override
    protected int getListLayout() {
        return R.layout.fragment_ingredient_list;
    }

    @Override
    public List<Ingredient> getList(){
        try {
            Dao<Ingredient, Integer> dao = getHelper().getIngredientsDao();
            List<Ingredient> list = dao.queryForAll();
            return list;
        } catch (SQLException e){
            System.out.println("##### ERROR - Impossible de récupérer les plats");
            return new ArrayList<>();
        }

    }

    @Override
    protected View createElementView(final Ingredient ingredient, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.list_ingredient, null);
        String minutes = " min";

        TextView ingredientName = vi.findViewById(R.id.list_ingredient_name);
        ingredientName.setText(ingredient.getName());

        Bundle bundle = getArguments();
        if (bundle != null) {
            title.setText(bundle.getString("title"));
        }

        ll = vi.findViewById(R.id.linearLayout);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), IngredientActivity.class);

                //intent.putExtra("title", ingredient.getName());
                //startActivity(intent);

            }
        });


        return vi;
    }

}
