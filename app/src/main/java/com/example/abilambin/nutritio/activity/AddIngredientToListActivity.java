package com.example.abilambin.nutritio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.dao.DatabaseHelper;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.IngredientList;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientEntryRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientRestCaller;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import adapter.AddIngredientToGroceryAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddIngredientToListActivity<T extends IngredientList> extends AppCompatActivity {

    @BindView(R.id.etSearch)
    EditText etSearch;

    @BindView(R.id.lvIngredients)
    ListView lvIngredients;

    IngredientRestCaller ingredientRestCaller = new IngredientRestCaller();

    private DatabaseHelper databaseHelper;

    private List<Ingredient> ingredients;
    private AddIngredientToGroceryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient_to_list);
        ButterKnife.bind(this);

        // Add Text Change Listener to EditText
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                adapter.getFilter().filter(s.toString());
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
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (CannotAuthenticateUserException e) {
            e.printStackTrace();
        } finally {
            adapter = new AddIngredientToGroceryAdapter(this, ingredients);
            lvIngredients.setAdapter(adapter);
        }
    }

    protected DatabaseHelper getHelper(){
        if(databaseHelper == null){
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
}
