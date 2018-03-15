package com.example.abilambin.nutritio.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.Person;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.GrocerieRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientEntryRestCaller;
import com.example.abilambin.nutritio.restApi.specific.PersonRestCaller;
import com.example.abilambin.nutritio.restApi.specific.StockRestCaller;
import com.example.abilambin.nutritio.utils.NConstants;
import com.example.abilambin.nutritio.utils.PersonSession;
import com.example.abilambin.nutritio.utils.Utils;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class IngredientEntryActionBarCallBack extends AbstractActionBarCallBack {

    private IngredientEntry selectedEntry;
    private int currentFragment;
    private PersonSession session = PersonSession.getInstance();

    @Override
    protected void addTo() {
        int userId = Utils.getUserId((Activity) getContext());
        PersonRestCaller personRestCaller = new PersonRestCaller();
        Person user = null;
        try {
            user = (Person) personRestCaller.get(userId);
        } catch (ExecutionException | WebServiceCallException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        if (user != null) {
            if (currentFragment == NConstants.COURSES_FRAGMENT) {
                user.getStock().getIngredientEntries().add(selectedEntry);
                int index = getIngredientEntryIndex(selectedEntry, user.getGrocerie().getIngredientEntries());
                if(index != -1) {
                    user.getGrocerie().getIngredientEntries().remove(index);
                }
                session.invalidateGrocerie();
                session.invalidateStock();
                updateGroceries(user.getGrocerie());
                updateStock(user.getStock());
            } else if (currentFragment == NConstants.STOCK_FRAGMENT) {
                user.getGrocerie().getIngredientEntries().add(selectedEntry);
                int index = getIngredientEntryIndex(selectedEntry, user.getStock().getIngredientEntries());
                if(index != -1) {
                    user.getStock().getIngredientEntries().remove(index);
                }
                session.invalidateGrocerie();
                session.invalidateStock();
                updateGroceries(user.getGrocerie());
                updateStock(user.getStock());
            }
        }
    }

    private int getIngredientEntryIndex(IngredientEntry entry, List<IngredientEntry> list){
        int i = 0;
        for(IngredientEntry entry1 : list){
            if(entry.getId().equals(entry1.getId())){
                return i;
            }
            i++;
        }
        return -1;

    }

    private void updateStock(Stock stock) {
        StockRestCaller stockRestCaller = new StockRestCaller();
        try {
            stockRestCaller.update(stock);
        } catch (ExecutionException | WebServiceCallException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private void updateGroceries(Grocerie grocerie){
        GrocerieRestCaller restCaller = new GrocerieRestCaller();
        try {
            restCaller.update(grocerie);
        } catch (ExecutionException | WebServiceCallException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
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
                GenericRestCaller restCaller = null;
                if(currentFragment == NConstants.STOCK_FRAGMENT) {
                    restCaller = new StockRestCaller();
                } else if (currentFragment == NConstants.COURSES_FRAGMENT){
                    restCaller = new GrocerieRestCaller();
                }
                if (restCaller != null) {
                    Person user = null;
                    try {
                        user = (Person) new PersonRestCaller().get(Utils.getUserId((Activity) getContext()));
                    } catch (ExecutionException | WebServiceCallException | CannotAuthenticateUserException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }

                    if(user != null) {
                        if (currentFragment == NConstants.COURSES_FRAGMENT) {
                            int index = getIngredientEntryIndex(selectedEntry, user.getGrocerie().getIngredientEntries());
                            if(index != -1) {
                                user.getGrocerie().getIngredientEntries().remove(index);
                            }
                            session.invalidateGrocerie();
                            updateGroceries(user.getGrocerie());
                        } else if (currentFragment == NConstants.STOCK_FRAGMENT) {
                            int index = getIngredientEntryIndex(selectedEntry, user.getStock().getIngredientEntries());
                            if(index != -1) {
                                user.getStock().getIngredientEntries().remove(index);
                            }
                            session.invalidateStock();
                            updateStock(user.getStock());
                        }
                    }
                    Toast.makeText(getContext(), "Suppression confirmée", Toast.LENGTH_LONG).show();

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

                if(currentFragment == NConstants.COURSES_FRAGMENT){
                    session.invalidateGrocerie();
                } else if (currentFragment == NConstants.STOCK_FRAGMENT){
                    session.invalidateStock();
                }

                IngredientEntryRestCaller restCaller = new IngredientEntryRestCaller();
                try {
                    restCaller.update(selectedEntry);
                } catch (ExecutionException | WebServiceCallException | CannotAuthenticateUserException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
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

    public void setCurrentFragment(int currentFragment){
        this.currentFragment = currentFragment;
    }
}