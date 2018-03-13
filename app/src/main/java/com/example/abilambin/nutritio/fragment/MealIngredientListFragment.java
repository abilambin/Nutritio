package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.IngredientActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import viewHolder.GenericViewHolder;

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
        return R.layout.list_ingredient;
    }

    @Override
    protected List<IngredientEntry> getList() {
        Bundle bundle = getActivity().getIntent().getExtras();
        recipe = (Recipe) bundle.get("recipe");

        return recipe.getIngredientEntries();
    }

    @Override
    protected GenericViewHolder getViewHolder(View view) {
        return null;
    }


    protected View createElementView(final IngredientEntry ingredientEntry, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.item_ingredient, null);

        final Ingredient ingredient = ingredientEntry.getIngredient();


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
                IngredientEntryActionBarCallBack bar = new IngredientEntryActionBarCallBack();

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
