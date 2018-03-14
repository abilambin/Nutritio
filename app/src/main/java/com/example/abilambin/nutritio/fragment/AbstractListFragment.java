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

import java.io.Serializable;
import java.util.List;

import adapter.GenericAdapter;
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
        recyclerView.setAdapter(new GenericAdapter<>(elements, getItem()));

        return view;
    }

    protected abstract int getItem();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getListLayout();

    protected abstract List<T> getList();




}
