package com.example.abilambin.nutritio.fragment;

import android.app.Activity;
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
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.GenericRestCaller;
import com.example.abilambin.nutritio.restApi.specific.IngredientEntryRestCaller;

import java.util.concurrent.ExecutionException;

/**
 * Created by abilambin on 06/03/2018.
 */

class ActionBarCallBack implements ActionMode.Callback {
    private int selectedItemId;

    private IngredientEntry selectedEntry;

    private Context context;

    public void setContext(Context context){
        this.context = context;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {

            /*Object[] tags = (Object[]) mode.getTag();
            Activity activity = (Activity) tags[0];
            int index = (Integer) tags[1];*/

            case R.id.item_delete:
                // Message de confirmation :

                // Puis, si oui, on retire selectedEntry de la liste concernée (courses ou stock)
                mode.finish();
                return true;
            case R.id.item_edit:
                edit();
                mode.finish();
                return true;

            case R.id.item_addTo:
                return true;
        }
        return false;
    }

    private void edit() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.edit_ingredient_dialog);

        final EditText editText = dialog.findViewById(R.id.ingredientQuantity);
        editText.setText(selectedEntry.getAmount()+"");

        final Spinner spinner = dialog.findViewById(R.id.spinner);

        String compareValue = getCompareValue(selectedEntry);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spinner.setSelection(spinnerPosition);
        }

        Button valider = dialog.findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener(){
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
                } catch (WebServiceCallException e) {
                    e.printStackTrace();
                } catch (CannotAuthenticateUserException e) {
                    e.printStackTrace();
                }

                dialog.cancel();
            }
        });

        Button cancel = dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

        //AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //builder.set("Title");

        // On crée l'input
        //final EditText input = new EditText(context);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        //builder.setView(input);

        // Set up the buttons
        //builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        //    @Override
        //    public void onClick(DialogInterface dialog, int which) {
        //        m_Text = input.getText().toString();
        //    }
        //});
        //builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        //    @Override
        //    public void onClick(DialogInterface dialog, int which) {
        //        dialog.cancel();
        //    }
        //});

        //builder.show();
    }

    private String getCompareValue(IngredientEntry entry){
        String s = entry.getUnitText();
        s = (s.charAt(0) + "").toUpperCase() + s.substring(1);
        if (s.equals("Grammes") || s.equals("Millilitres")) {
            s += " (" + entry.getUnitSmallText() + ")";
        }
        return s;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        mode.getMenuInflater().inflate(R.menu.contextual_menu, menu);

        //MenuItem item = menu.findItem(R.id.item_addTo);
        //Spinner spinner = (Spinner) MenuItemCompat.getActionView(item); // get the spinner
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(onItemSelectedListener);
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        Object[] tags = (Object[]) mode.getTag();
        Activity activity = (Activity) tags[0];
        View view = (View) activity.getCurrentFocus();

    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub

        mode.setTitle("");
        return false;
    }

    public int getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(int selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public IngredientEntry getSelectedEntry() {
        return selectedEntry;
    }

    public void setSelectedEntry(IngredientEntry selectedEntry) {
        this.selectedEntry = selectedEntry;
    }
}