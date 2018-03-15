package com.example.abilambin.nutritio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.viewHolder.GenericViewHolder;
import com.example.abilambin.nutritio.viewHolder.IngredientEntryViewHolder;
import com.example.abilambin.nutritio.viewHolder.MealViewHolder;
import com.example.abilambin.nutritio.viewHolder.RecipeViewHolder;

import java.io.Serializable;
import java.util.List;


public class GenericAdapter<T extends Serializable> extends RecyclerView.Adapter<GenericViewHolder> {

    List<T> list;
    private int simple_list_item;
    private int currentFragment;

    public GenericAdapter(List<T> list, int simple_list_item, int currentFragment) {
        this.list = list;
        this.simple_list_item = simple_list_item;
        this.currentFragment = currentFragment;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(simple_list_item,parent,false);

        return getViewHolder(view);
    }

    private GenericViewHolder getViewHolder(View view) {
        if(list != null) {
            T elem = list.get(0);

            if (elem instanceof Meal) {
                return new MealViewHolder(view);
            }

            if (elem instanceof IngredientEntry) {
                return new IngredientEntryViewHolder(view, currentFragment);
            }

            if (elem instanceof Recipe) {
                return new RecipeViewHolder(view);
            }
        }

        return null;

    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        T element = list.get(position);
        holder.setPosition(position);
        holder.bind(element);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
