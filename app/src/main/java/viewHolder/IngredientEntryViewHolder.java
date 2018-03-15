package viewHolder;

import android.content.Intent;
import android.text.Html;
import android.view.ActionMode;
import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.IngredientActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.IngredientEntry;
import com.example.abilambin.nutritio.fragment.AbstractActionBarCallBack;
import com.example.abilambin.nutritio.fragment.IngredientEntryActionBarCallBack;

/**
 * Created by abilambin on 13/03/2018.
 */

public class IngredientEntryViewHolder extends GenericViewHolder<IngredientEntry> {


    protected ActionMode mActionMode;
    private int currentFragment;

    public IngredientEntryViewHolder(View itemView, int currentFragment) {
        super(itemView);
        this.currentFragment = currentFragment;
    }


    @Override
    public void bind(IngredientEntry entry) {
        super.bind(entry);

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

    @Override
    public void onClick(View itemView) {

        Intent intent = new Intent(itemView.getContext(), IngredientActivity.class);

        // On appelle l'activité de visualisation de l'ingrédient concerné
        intent.putExtra("ingredient", getElement());
        itemView.getContext().startActivity(intent);
    }

    @Override
    public AbstractActionBarCallBack getActionBarCallBack() {
        IngredientEntryActionBarCallBack bar = new IngredientEntryActionBarCallBack();
        bar.setSelectedEntry(getElement());
        bar.setContext(itemView.getContext());
        bar.setCurrentFragment(this.currentFragment);
        return bar;
    }

    @Override
    public boolean onLongClick(View itemView) {
        super.onLongClick(itemView);
        return true;
    }

}
