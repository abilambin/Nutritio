package com.example.abilambin.nutritio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.fragment.IntakesFragment;
import com.example.abilambin.nutritio.restApi.specific.GrocerieRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientEntryRestCaller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import adapter.AddIngredientToListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateIngredientEntryActivity extends AppCompatActivity {


    @BindView(R.id.ingredientTitle)
    TextView title;

    @BindView(R.id.ingredientCategory)
    TextView category;

    @BindView(R.id.ingredientBrand)
    TextView brand;

    @BindView(R.id.ingredientQuantity)
    EditText quantity;

    @BindView(R.id.spinner)
    Spinner unit;

    @BindView(R.id.submit)
    Button submit;

    private AddIngredientToListAdapter adapter;

    private IngredientEntryRestCaller ingredientEntryRestCaller = new IngredientEntryRestCaller();

    private GrocerieRestCaller groceriesRestCaller = new GrocerieRestCaller();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ingredient_entry);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        final Ingredient ingredient = (Ingredient) bundle.get("ingredient");

        final IntakesFragment fg = new IntakesFragment();
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        quantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int q = 0;
                // Si la quantité n'est pas inscrite, on considère zéro
                try {
                    q = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } finally {
                    fg.setQuantity(q);
                    fg.generateIntakes();
                }
            }
        });

        ingredients.add(ingredient);
        fg.setIngredients(ingredients);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.intakesContainer,fg)
                .addToBackStack("frag")
                .commit();

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Créer IngredientEntry + l'ajouter aux courses
                IngredientEntry entry = new IngredientEntry();
                entry.setIngredient(ingredient);
                entry.setAmount(Integer.parseInt(quantity.getText().toString()));

                try {
                    ingredientEntryRestCaller.create(entry);
                    //TODO : changer '1' par l'id de l'utilisateur
                    Grocerie grocerie = (Grocerie) groceriesRestCaller.get(1);
                    grocerie.add(entry);
                    groceriesRestCaller.update(grocerie);

                    Grocerie g = (Grocerie) groceriesRestCaller.get(1);
                    int i = 1;
                    for (IngredientEntry e : g.getIngredientEntries()) {
                        Log.d("INGREDIENTS :" + i + " : ",e.getIngredient().getName());

                        i++;
                    }


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (WebServiceCallException e) {
                    e.printStackTrace();
                } catch (CannotAuthenticateUserException e) {
                    e.printStackTrace();
                }


            }

        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();
        Ingredient ingredient = (Ingredient) bundle.get("ingredient");

        title.setText(ingredient.getName());
        category.setText(Html.fromHtml("<b>Catégorie : </b>" +ingredient.getCategoryText()));
        brand.setText(Html.fromHtml("<b>Marque : </b>"+ingredient.getBrand()));
        quantity.setText("100");

    }

}
