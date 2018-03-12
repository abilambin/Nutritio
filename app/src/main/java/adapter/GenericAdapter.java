package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abilambin.nutritio.bdd.model.Meal;

import java.util.List;

import viewHolder.GenericViewHolder;
import viewHolder.MealViewHolder;

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

        if (list.get(0) instanceof Meal) {
            return new MealViewHolder(view);
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
