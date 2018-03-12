package com.example.abilambin.nutritio.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Html;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.IngredientActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientEntryRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientRestCaller;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MealIngredientListFragment extends AbstractListFragment<IngredientEntry>{

    LinearLayout ll;

    ArrayList<IngredientEntry> ingredientEntries;

    private String typeName;

    private ActionMode mActionMode;

    @BindView(R.id.addIngredientButton)
    FloatingActionButton addIngredientButton;

    private Recipe recipe;

    @Override
    protected int getItem() {
        return 0;
    }

    protected int getListLayout() {
        return R.layout.fragment_ingredient_list;
    }

    @Override
    protected List<IngredientEntry> getList() {
        Bundle bundle = getActivity().getIntent().getExtras();
        recipe = (Recipe) bundle.get("recipe");

        return recipe.getIngredientEntries();
    }


    protected View createElementView(final IngredientEntry ingredientEntry, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.list_ingredient, null);

        final Ingredient ingredient = ingredientEntry.getIngredient();
        String brand = ingredient.getBrand();
        brand = (brand == null)?"":brand+", ";

        TextView ingredientName = vi.findViewById(R.id.list_ingredient_name);
        TextView ingredientBrand = vi.findViewById(R.id.list_ingredient_brand);
        TextView ingredientQuantity = vi.findViewById(R.id.list_ingredient_quantity);

        ingredientName.setText(Html.fromHtml("<b>"+ingredient.getName()+"</b>"));
        ingredientBrand.setText(brand);
        ingredientQuantity.setText(ingredientEntry.getAmount() + ingredientEntry.getUnitSmallText());


        // On récupère le layout de l'ingrédient
        ll = vi.findViewById(R.id.ingredientContainer);

        // ON CLICK -> READ
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IngredientActivity.class);

                // On appelle l'activité de visualisation de l'ingrédient concerné
                intent.putExtra("ingredient", ingredient);
                startActivity(intent);

            }
        });

        // ON LONG CLICK -> UPDATE, DELETE
        ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Génération d'un retour haptique
                ll.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                //TODO : Changer le style de l'élément sélectionné
                ll.setBackgroundColor(222222);


                // On génère la barre de modification de l'ingrédient
                ActionBarCallBack bar = new ActionBarCallBack();

                // On ajoute l'id de la vue de l'ingrédient à la barre
                bar.setSelectedEntry(ingredientEntry);
                bar.setContext(v.getContext());

                mActionMode = getActivity().startActionMode(bar);
                Object[] tags = new Object[2];
                tags[0] = v.getRootView().getContext();
                mActionMode.setTag(tags);
                return true;
            }
        });


        return vi;
    }

    public Class getAddIngredientActivity() {
        return null;
    }

    public MealIngredientListFragment(){

    }
}
