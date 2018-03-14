package com.example.abilambin.nutritio.fragment;

import android.app.Dialog;
import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.DashBoard;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientEntryRestCaller;
import com.example.abilambin.nutritio.restApi.specific.MealRestCaller;

import java.util.concurrent.ExecutionException;

/**
 * Created by abilambin on 06/03/2018.
 */

public class MealActionBarCallBack extends AbstractActionBarCallBack {

    private Meal selectedEntry;

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
                GenericRestCaller<Meal> restCaller = new MealRestCaller();

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

    public void setSelectedEntry(Meal selectedEntry) {
        this.selectedEntry = selectedEntry;
    }
}