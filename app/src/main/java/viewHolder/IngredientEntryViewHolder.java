package viewHolder;

import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.bdd.model.Meal;

import java.util.Date;

/**
 * Created by abilambin on 13/03/2018.
 */

public class IngredientEntryViewHolder extends GenericViewHolder<IngredientEntry> {

    public IngredientEntryViewHolder(View itemView) {
        super(itemView);
    }


    @Override
    public void bind(IngredientEntry entry) {

        final Ingredient ingredient = entry.getIngredient();

        String brand = ingredient.getBrand();
        brand = (brand == null)?"":brand+", ";


        TextView nameText = itemView.findViewById(R.id.name);
        nameText.setText(Html.fromHtml("<b>"+ingredient.getName()+"</b>"));

        TextView hourText = itemView.findViewById(R.id.unit);
        hourText.setText(entry.getAmount() + entry.getUnitSmallText());

        TextView brandText = itemView.findViewById(R.id.brand);
        brandText.setText(brand);

    }

}
