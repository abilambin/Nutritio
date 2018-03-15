package com.example.abilambin.nutritio.fragment;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abilambin.nutritio.R;

/**
 * Created by abilambin on 14/03/2018.
 */

public abstract class AbstractActionBarCallBack implements ActionMode.Callback  {

    private Context context;

    public Context getContext() { return context; }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        mode.setTitle("");
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_delete:
                delete();
                mode.finish();
                return true;
            case R.id.item_edit:
                edit();
                mode.finish();
                return true;
            case R.id.item_addTo:
                addTo();
                mode.finish();
                return true;
        }

        return false;
    }

    protected abstract void addTo();

    protected abstract void edit();

    protected abstract void delete();

}
