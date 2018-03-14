package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.text.Html;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.RecipeActivity;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.specific.MealRestCaller;
import com.example.abilambin.nutritio.restApi.specific.RecipeRestCaller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import viewHolder.GenericViewHolder;
import viewHolder.RecipeViewHolder;

public class RecipeFragment extends AbstractListFragment<Recipe> {

    LinearLayout ll;

    @BindView(R.id.title)
    protected TextView title;

    RecipeRestCaller recipeRestCaller = new RecipeRestCaller();

    private ActionMode mActionMode;
    RecipeViewHolder recipeViewHolder;


    @Override
    protected int getItem() {
        return R.layout.item_recipe;
    }

    @Override
    protected int getListLayout() {
        return R.layout.list_recipe;
    }

    @Override
    public List<Recipe> getList(){
        try {
            //On récupère la liste des ingrédients récupéré par appel rest
            List<Recipe> list = (List<Recipe>) recipeRestCaller.getAll();

            //Si elle est null, alors on en crée une vide
            if (list == null) return new ArrayList<>();

            return list;

        } catch (WebServiceCallException e) {
            System.out.println("##### ERROR - Impossible de récupérer les recettes :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (InterruptedException e) {
            System.out.println("##### ERROR - Impossible de récupérer les recettes :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ExecutionException e) {
            System.out.println("##### ERROR - Impossible de récupérer les recettes :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (CannotAuthenticateUserException e) {
            System.out.println("##### ERROR - Impossible de récupérer les recettes :");
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
    /**
     * Génère la vue de l'ingrédient en paramètre (y ajoute les listener d'évênements)
     * @param recipe le plat à afficher
     * @param inflater
     * @return
     */
    protected View createElementView(final Recipe recipe, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.list_recipe, null);


        String text = Html.fromHtml("<b> Ingrédients : </b>") +"\n";
        for (IngredientEntry ingredientEntry : recipe.getIngredientEntries()) {
            text += ingredientEntry.getIngredient().getName() + " : " + ingredientEntry.getAmount() + " " +ingredientEntry.getUnitSmallText() + "\n";
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            title.setText(bundle.getString("title"));
        }


        ll = vi.findViewById(R.id.linearLayout);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), RecipeActivity.class);

                //intent.putExtra("name", meal.getName());
                //intent.putExtra("description", meal.getRecipe());
                //startActivity(intent);

            }
        });

        return vi;
    }






}
