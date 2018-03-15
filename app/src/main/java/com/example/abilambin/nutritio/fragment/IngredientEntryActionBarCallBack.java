package com.example.abilambin.nutritio.fragment;

import android.app.Dialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientEntryRestCaller;

import java.util.concurrent.ExecutionException;


public class IngredientEntryActionBarCallBack extends AbstractActionBarCallBack {

    private IngredientEntry selectedEntry;


    @Override
    protected void addTo() {

    }

    @Override
    protected void delete() {
        //Message de confirmation
        final Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.confirmation_dialog);
        // Puis, si oui, on retire selectedEntry de la liste concernée (courses ou stock)
        Button yes = dialog.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedEntry.setAmount(0);
                GenericRestCaller<IngredientEntry> restCaller = new IngredientEntryRestCaller();
                try {
                    restCaller.update(selectedEntry);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
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

    @Override
    protected void edit() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.edit_ingredient_dialog);

        final EditText editText = dialog.findViewById(R.id.ingredientQuantity);
        editText.setText(selectedEntry.getAmount() + "");

        final Spinner spinner = dialog.findViewById(R.id.spinner);

        String compareValue = getCompareValue(selectedEntry);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spinner.setSelection(spinnerPosition);
        }

        Button valider = dialog.findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedEntry.setAmount(Integer.parseInt(editText.getText().toString()));
                selectedEntry.setUnit(IngredientEntry.getUnitFromText(spinner.getSelectedItem().toString()));

                GenericRestCaller<IngredientEntry> restCaller = new IngredientEntryRestCaller();
                try {
                    restCaller.update(selectedEntry);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                } catch (WebServiceCallException e) {
                    e.printStackTrace();
                } catch (CannotAuthenticateUserException e) {
                    e.printStackTrace();
                }

                dialog.cancel();
            }
        });

        Button cancel = dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    }



    private String getCompareValue(IngredientEntry entry) {
        String s = entry.getUnitText();
        s = (s.charAt(0) + "").toUpperCase() + s.substring(1);
        if (s.equals("Grammes") || s.equals("Millilitres")) {
            s += " (" + entry.getUnitSmallText() + ")";
        }
        return s;
    }


    public void setSelectedEntry(IngredientEntry selectedEntry) {
        this.selectedEntry = selectedEntry;
    }
}