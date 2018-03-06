package com.example.abilambin.nutritio.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.IngredientActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.ingredientList.IngredientList;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.AuthenticateUser;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.GrocerieRestCaller;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;

import static com.example.abilambin.nutritio.restApi.AuthenticateUser.getInstance;

public abstract class IngredientListFragment<T extends IngredientList> extends AbstractListFragment<IngredientEntry> {

    LinearLayout ll;

    GenericRestCaller<T> restCaller;

    private ActionMode mActionMode;

    @Override
    protected int getListLayout() {
        return R.layout.fragment_groceries;
    }

    @Override
    public List<IngredientEntry> getList(){
        try {
            //On récupère la liste des ingrédients récupéré par appel rest
            T list = restCaller.get(1);

            //Si elle est null, alors on en crée une vide
            if (list == null) return new ArrayList<>();

            return list.getIngredientEntries();

        } catch (WebServiceCallException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (InterruptedException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ExecutionException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (CannotAuthenticateUserException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    /**
     * Génère la vue de l'ingrédient en paramètre (y ajoute les listener d'évênements)
     * @param entry l'entrée Ingrédient/Quantité à afficher
     * @param inflater
     * @return
     */
    @Override
    protected View createElementView(final IngredientEntry entry, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.list_ingredient, null);

        final Ingredient ingredient = entry.getIngredient();
        String brand = ingredient.getBrand();
        brand = (brand == null)?"":brand+", ";

        TextView ingredientName = vi.findViewById(R.id.list_ingredient_name);
        TextView ingredientBrand = vi.findViewById(R.id.list_ingredient_brand);
        TextView ingredientQuantity = vi.findViewById(R.id.list_ingredient_quantity);

        ingredientName.setText(Html.fromHtml("<b>"+ingredient.getName()+"</b>"));
        ingredientBrand.setText(brand);
        ingredientQuantity.setText(entry.getAmount() + entry.getUnitSmallText());


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
                bar.setSelectedItemId(v.getId());
                bar.setSelectedEntry(entry);

                mActionMode = getActivity().startActionMode(bar);

                return true;
            }
        });


        return vi;
    }

    public void onStop() {
        if (mActionMode != null) mActionMode.finish();

        super.onStop();
    }




}
