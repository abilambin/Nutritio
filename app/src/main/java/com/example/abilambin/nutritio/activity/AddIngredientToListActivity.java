package com.example.abilambin.nutritio.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.adapter.AddIngredientToListAdapter;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.specific.IngredientRestCaller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class AddIngredientToListActivity extends AppCompatActivity {

    @BindView(R.id.etSearch)
    EditText etSearch;

    @BindView(R.id.lvIngredients)
    ListView lvIngredients;

    IngredientRestCaller ingredientRestCaller = new IngredientRestCaller();

    private AddIngredientToListAdapter adapter;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient_to_list);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();
        // Add Text Change Listener to EditText
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                if (adapter != null) adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Ingredient>  ingredients = new ArrayList<>();

        try{
            ingredients = ingredientRestCaller.getAll();
        } catch (WebServiceCallException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (CannotAuthenticateUserException e) {
            e.printStackTrace();
        } finally {
            adapter = getAdapter(this, ingredients);
            String typeName = (String) bundle.get("typeName");
            adapter.setTypeId((Integer) bundle.get(typeName));
            lvIngredients.setAdapter(adapter);
        }
    }

    public abstract AddIngredientToListAdapter getAdapter(Context ctx, List<Ingredient> ingredients);
}
