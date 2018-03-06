package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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

    @Override
    protected int getListLayout() {
        return R.layout.fragment_groceries;
    }

    @Override
    public List<IngredientEntry> getList(){
        try {
            AuthenticateUser user = getInstance();
            user.setAuthenticateInfo("admin", "cam3113?");
            T list = restCaller.get(1);

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


        ll = vi.findViewById(R.id.ingredientContainer);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IngredientActivity.class);

                intent.putExtra("ingredient", ingredient);
                startActivity(intent);

            }
        });

        ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ll.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                ll.setBackgroundColor(222222);
                ActionBarCallBack bar = new ActionBarCallBack();
                getActivity().startActionMode(bar);
                return true;
            }
        });


        return vi;
    }

}
