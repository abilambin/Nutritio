package com.example.abilambin.nutritio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.fragment.IntakesFragment;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientEntryRestCaller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class CreateIngredientEntryActivity<T> extends AppCompatActivity {

    private int id;

    public void setId(int id){
        this.id = id;
    }


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

    GenericRestCaller<T> tGenericRestCaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ingredient_entry);
        ButterKnife.bind(this);

        tGenericRestCaller = getRestCaller();

        Bundle bundle = getIntent().getExtras();
        final Ingredient ingredient = (Ingredient) bundle.get("ingredient");
        id = (int) bundle.get("typeId");

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
                T type = getType();
                IngredientEntry newIngredientEntry = createEntry(ingredient, type);
                if(type != null){
                    updateType(newIngredientEntry, type);
                }
            }
        });

    }

    private IngredientEntry createEntry(Ingredient ingredient, T type) {
        IngredientEntry newIngredientEntry = new IngredientEntry();
        newIngredientEntry = addTypeToEntry(newIngredientEntry, type);
        newIngredientEntry.setAmount(Integer.parseInt(quantity.getText().toString()));
        newIngredientEntry.setUnit(IngredientEntry.getUnitFromText(unit.getSelectedItem().toString()));
        newIngredientEntry.setIngredient(ingredient);
        newIngredientEntry.setId(null);

        GenericRestCaller<IngredientEntry> ingredientEntryGenericRestCaller = new IngredientEntryRestCaller();
        IngredientEntry newEntry = null;
        try {
            newEntry = ingredientEntryGenericRestCaller.create(newIngredientEntry);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (WebServiceCallException e) {
            e.printStackTrace();
        } catch (CannotAuthenticateUserException e) {
            e.printStackTrace();
        }

        return newEntry;
    }

    private void updateType(IngredientEntry entry, T type) {
        type = addEntryToType(entry, type);
        try {
            tGenericRestCaller.update(type);
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

    private T getType() {
        T type = null;
        try {
            type = tGenericRestCaller.get(id);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (WebServiceCallException e) {
            e.printStackTrace();
        } catch (CannotAuthenticateUserException e) {
            e.printStackTrace();
        }
        return type;
    }

    public abstract T addEntryToType(IngredientEntry entry, T type);

    public abstract GenericRestCaller<T> getRestCaller();

    public abstract IngredientEntry addTypeToEntry(IngredientEntry entry, T type);

    @Override
    protected void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();
        Ingredient ingredient = (Ingredient) bundle.get("ingredient");

        title.setText(ingredient.getName());
        category.setText(Html.fromHtml("<b>Catégorie : </b>" +ingredient.getCategoryText()));
        String marque = ingredient.getBrand();
        if (marque != null) {
            brand.setText(Html.fromHtml("<b>Marque : </b>"+marque));
        }
        quantity.setText("100");

    }

    @Override
    public void onBackPressed() {

    }

}
