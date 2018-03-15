package com.example.abilambin.nutritio.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.adapter.GenericAdapter;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by serial on 09/02/2018.
 */

public abstract class AbstractListFragment<T extends Serializable> extends Fragment {

    private RecyclerView recyclerView;

    private List<T> elements;

    public abstract String getTitle();

    @BindView(R.id.title)
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getListLayout(), container, false);
        ButterKnife.bind(this, view);

        textView.setText(getTitle());
        elements = getList();

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new GenericAdapter<>(elements, getItem(), getCurrentFragment()));

        return view;
    }

    protected abstract int getItem();

    protected abstract int getCurrentFragment();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getListLayout();

    protected abstract List<T> getList();

    public List<IngredientEntry> removeIngredientWithoutQuantity(List<IngredientEntry> list) {
        List<IngredientEntry> entries = new ArrayList<>();

        if(list != null && !list.isEmpty()) {
            for (IngredientEntry ingredientEntry : list) {
                if(ingredientEntry.getAmount() != 0){
                    entries.add(ingredientEntry);
                }
            }
        }

        return entries;

    }




}
