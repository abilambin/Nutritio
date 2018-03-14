package com.example.abilambin.nutritio.fragment;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.MealRestCaller;
import com.example.abilambin.nutritio.restApi.specific.RecipeRestCaller;

import java.util.concurrent.ExecutionException;

/**
 * Created by abilambin on 06/03/2018.
 */

public class RecipeActionBarCallBack extends AbstractActionBarCallBack {

    private Recipe selectedEntry;

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void addTo() {

    }

    protected void delete() {
        //TODO
        //Message de confirmation
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.confirmation_dialog);
        // Puis, si oui, on retire selectedEntry de la liste concernée (courses ou stock)
        Button yes = dialog.findViewById(R.id.yes);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenericRestCaller<Recipe> restCaller = new RecipeRestCaller();

                try {
                    restCaller.delete(selectedEntry.getId());

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (WebServiceCallException e) {
                    e.printStackTrace();
                } catch (CannotAuthenticateUserException e) {
                    e.printStackTrace();
                }
                dialog.cancel();
            }
        });

        Button no = dialog.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }


    protected void edit() {

    }

    public void setSelectedEntry(Recipe selectedEntry) {
        this.selectedEntry = selectedEntry;
    }
}