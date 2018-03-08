package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.CreateIngredientEntryActivity;
import com.example.abilambin.nutritio.activity.IngredientActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.ingredientList.IngredientList;

import java.util.List;

/**
 * Created by serial on 23/02/2018.
 */

public class AddIngredientToListAdapter<T extends IngredientList> extends AbstractListAdapter<Ingredient> {

    public AddIngredientToListAdapter(Context context, List<Ingredient> items){
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
        LinearLayout infoContainer;
        TextView name, category;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        final Ingredient ingredient = getFilteredElements().get(position);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getInflater().inflate(R.layout.list_element_add_ingredient, null);
            holder.infoContainer = convertView.findViewById(R.id.ingredientContainer);

            holder.name = convertView.findViewById(R.id.list_ingredient_name);
            holder.category = convertView.findViewById(R.id.list_ingredient_category);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(getFilteredElements().get(position).getName());
        holder.category.setText(getFilteredElements().get(position).getBrand());

        // ON CLICK -> READ
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateIngredientEntryActivity.class);

                // On appelle l'activité de visualisation de l'ingrédient concerné
                intent.putExtra("ingredient", ingredient);
                v.getContext().startActivity(intent);

            }
        });

        return convertView;
    }


}
