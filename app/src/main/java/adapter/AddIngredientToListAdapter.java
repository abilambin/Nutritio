package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.CreateIngredientEntryActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.ingredientList.IngredientList;

import java.util.List;

/**
 * Created by serial on 23/02/2018.
 */

public class AddIngredientToListAdapter extends AbstractListAdapter<Ingredient> {

    protected Class createActivityClass;

    public Class getCreateActivityClass() {
        return createActivityClass;
    }

    public AddIngredientToListAdapter(Context context, List<Ingredient> items, Class createActivityClass){
        super(context, items);
        this.createActivityClass = createActivityClass;
    }


    private int typeId;

    public void setTypeId(int id){
        typeId = id;
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
                Intent intent = new Intent(v.getContext(), getCreateActivityClass());

                // On appelle l'activité de visualisation de l'ingrédient concerné
                intent.putExtra("ingredient", ingredient);
                intent.putExtra("typeId", typeId);
                v.getContext().startActivity(intent);

            }
        });

        return convertView;
    }


}
