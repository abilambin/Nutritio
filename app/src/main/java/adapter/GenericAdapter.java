package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;

import java.util.List;

import viewHolder.GenericViewHolder;
import viewHolder.IngredientEntryViewHolder;
import viewHolder.MealViewHolder;
import viewHolder.RecipeViewHolder;

/**
 * Created by abilambin on 12/03/2018.
 */

public class GenericAdapter<T> extends RecyclerView.Adapter<GenericViewHolder> {

    List<T> list;
    private int simple_list_item;

    public GenericAdapter(List<T> list, int simple_list_item) {
        this.list = list;
        this.simple_list_item = simple_list_item;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(simple_list_item,parent,false);

        T element = list.get(0);
        if (element instanceof Meal) {
            return new MealViewHolder(view);
        } else if (element instanceof IngredientEntry) {
            return new IngredientEntryViewHolder(view);
        } else if (element instanceof Recipe) {
            return new RecipeViewHolder(view);
        }

        return new GenericViewHolder<T>(view);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        T element = list.get(position);
        holder.bind(element);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
