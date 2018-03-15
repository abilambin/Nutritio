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
import com.example.abilambin.nutritio.utils.PersonSession;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class CreateIngredientEntryActivity<T> extends AppCompatActivity {

    private int id;

    private IngredientEntry ingredientEntry;

    private PersonSession session = PersonSession.getInstance();

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


    // INTAKES
    @BindView(R.id.energy)
    TextView energy;

    @BindView(R.id.proteins)
    TextView proteins;
    @BindView(R.id.carbs)
    TextView carbs;
    @BindView(R.id.sugar)
    TextView sugar;
    @BindView(R.id.fat)
    TextView fat;
    @BindView(R.id.saturatedFat)
    TextView saturatedFat;
    @BindView(R.id.fibres)
    TextView fibres;


    // BUTTONS
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.cancel)
    Button cancel;

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

        quantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                    ingredientEntry = createEntry(ingredient, getType());
            }
        });

        ingredientEntry = createEntry(ingredient, getType());
        showAllIntakes();


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submit();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void showAllIntakes() {
        Ingredient ingredient = ingredientEntry.getIngredient();

        showOneIntakes(ingredient.getEnergy(), energy, "kcal");

        showOneIntakes(ingredient.getProtein(), proteins, "g");
        showOneIntakes(ingredient.getCarbohydrate(), carbs, "g");
        showOneIntakes(ingredient.getSugar(), sugar, "g");
        showOneIntakes(ingredient.getFat(), fat, "g");
        showOneIntakes(ingredient.getSaturatedFat(), saturatedFat, "g");
        showOneIntakes(ingredient.getFibre(), fibres, "g");

    }

    private void showOneIntakes(float intake, TextView textView, String unit) {
        final NumberFormat instance = NumberFormat.getNumberInstance();
        instance.setMinimumFractionDigits(0);
        instance.setMaximumFractionDigits(2);

        textView.setText(instance.format(intake)+unit);
    }

    private void submit() {
        //on refresh le cache des listes d'ingrédients possibles
        session.invalidateStock();
        session.invalidateGrocerie();
        if(getType() != null){
            updateType(this.ingredientEntry, getType());
        }
    }

    private IngredientEntry createEntry(Ingredient ingredient, T type) {
        IngredientEntry entry = new IngredientEntry();

        String q = quantity.getText().toString();
        if (q.equals("")) {
            q = "100";
        }

        entry = addTypeToEntry(entry, type);
        entry.setAmount(Integer.parseInt(q));
        entry.setUnit(IngredientEntry.getUnitFromText(unit.getSelectedItem().toString()));
        entry.setIngredient(ingredient);
        entry.setId(null);

        IngredientEntryRestCaller ingredientEntryGenericRestCaller = new IngredientEntryRestCaller();
        IngredientEntry newEntry = null;
        try {
            newEntry = (IngredientEntry) ingredientEntryGenericRestCaller.create(entry);
        } catch (ExecutionException | WebServiceCallException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return newEntry;
    }

    private void updateType(IngredientEntry entry, T type) {
        type = addEntryToType(entry, type);
        try {
            tGenericRestCaller.update(type);
        } catch (ExecutionException | WebServiceCallException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private T getType() {
        T type = null;
        try {
            type = tGenericRestCaller.get(id);
        } catch (ExecutionException | WebServiceCallException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
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
        finish();
    }

}
