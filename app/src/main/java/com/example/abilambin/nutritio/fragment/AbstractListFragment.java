package com.example.abilambin.nutritio.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.dao.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by serial on 09/02/2018.
 */

public abstract class AbstractListFragment<E> extends Fragment implements AdapterView.OnItemClickListener {

    protected DatabaseHelper databaseHelper;

    @BindView(R.id.fragmentList)
    LinearLayout listLayout;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getListLayout(), container, false);
        ButterKnife.bind(this, view);

        List<E> list = getList();
        for(int i = 0; i < list.size(); i++){
            View v = createElementView(list.get(i), inflater);
            listLayout.addView(v);
        }

        return view;
    }

    protected DatabaseHelper getHelper(){
        if(databaseHelper == null){
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    protected abstract int getListLayout();

    protected abstract List<E> getList();

    protected abstract View createElementView(E e, LayoutInflater inflater);


}
