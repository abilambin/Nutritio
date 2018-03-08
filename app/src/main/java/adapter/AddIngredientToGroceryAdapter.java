package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serial on 23/02/2018.
 */

public class AddIngredientToGroceryAdapter extends BaseAdapter implements Filterable {
    private List<Ingredient> originalsIngredients; //original values
    private List<Ingredient> filteredIngredients; //displayed values
    private LayoutInflater inflater;

    public AddIngredientToGroceryAdapter(Context context, List<Ingredient> ingredients){
        originalsIngredients = ingredients;
        filteredIngredients = ingredients;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filteredIngredients.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        LinearLayout infoContainer, buttonContainer;
        Button minus, plus;
        TextView name, category;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_element_add_ingredient, null);
            holder.infoContainer = convertView.findViewById(R.id.ingredientInfoContainer);
            holder.buttonContainer = convertView.findViewById(R.id.ingredientButtonContainer);

            holder.name = convertView.findViewById(R.id.list_ingredient_name);
            holder.category = convertView.findViewById(R.id.list_ingredient_cat);

            holder.minus = convertView.findViewById(R.id.minus);
            holder.plus = convertView.findViewById(R.id.plus);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(filteredIngredients.get(position).getName());
        holder.category.setText(filteredIngredients.get(position).getBrand());

        holder.minus.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast.makeText(v.getContext(), filteredIngredients.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Ingredient> filteredArrList = new ArrayList<>();
                if(originalsIngredients == null){
                    originalsIngredients = new ArrayList<>(filteredIngredients);
                }
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = originalsIngredients.size();
                    results.values = originalsIngredients;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < originalsIngredients.size(); i++) {
                        String name = originalsIngredients.get(i).getName();
                        String brand = originalsIngredients.get(i).getBrand();
                        brand = brand == null ? "" : brand;
                        if (name.toLowerCase().contains(constraint.toString()) || brand.toLowerCase().contains(constraint.toString())) {
                            filteredArrList.add(originalsIngredients.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = filteredArrList.size();
                    results.values = filteredArrList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredIngredients = (List<Ingredient>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
