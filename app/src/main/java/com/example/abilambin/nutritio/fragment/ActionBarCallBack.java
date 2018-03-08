package com.example.abilambin.nutritio.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.text.InputType;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.IngredientActivity;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;

/**
 * Created by abilambin on 06/03/2018.
 */

class ActionBarCallBack implements ActionMode.Callback {
    private int selectedItemId;

    private IngredientEntry selectedEntry;

    private String m_Text = "";



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
                View view = mode.getCustomView();
                Context ctx = mode.getCustomView().getContext();
                edit(ctx);
                mode.finish();
                return true;

            case R.id.item_addTo:
                return true;
        }
        return false;
    }

    private void edit(Context ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("Title");

        // On crée l'input
        final EditText input = new EditText(ctx);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
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
        // TODO Auto-generated method stub

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