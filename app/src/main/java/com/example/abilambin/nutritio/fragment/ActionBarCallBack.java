package com.example.abilambin.nutritio.fragment;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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



    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item_delete:
                // Message de confirmation :

                // Puis, si oui, on retire selectedEntry de la liste concernée (courses ou stock)
                mode.finish();
                return true;
            case R.id.item_edit:

                mode.finish();
                //Intent intent = new Intent(this, IngredientActivity.class);

                // On appelle l'activité de visualisation de l'ingrédient concerné
                //intent.putExtra("entry", selectedEntry);
                //startActivity(intent);
                return true;

            case R.id.item_addTo:
                return true;

            case R.id.add1:
                mode.finish();
                return true;

            case R.id.add2:
                mode.finish();
                return true;
        }
        return false;
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