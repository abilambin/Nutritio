package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;

import java.util.List;

/**
 * Created by serial on 23/02/2018.
 */

public class AddIngredientToGroceryAdapter extends AbstractListAdapter<Ingredient> {

    public AddIngredientToGroceryAdapter(Context context, List<Ingredient> items){
        super(context, items);
    }

    @Override
    public boolean filterCondition(Ingredient element, CharSequence constraint) {
        String name = element.getName();
        String brand = element.getBrand();
        brand = brand == null ? "" : brand;
        return name.toLowerCase().contains(constraint.toString()) || brand.toLowerCase().contains(constraint.toString());
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
            convertView = getInflater().inflate(R.layout.list_element_add_ingredient, null);
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
        holder.name.setText(getFilteredElements().get(position).getName());
        holder.category.setText(getFilteredElements().get(position).getBrand());

        holder.minus.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast.makeText(v.getContext(), getFilteredElements().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


}
