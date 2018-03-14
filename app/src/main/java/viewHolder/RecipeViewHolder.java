package viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.activity.IngredientActivity;
import com.example.abilambin.nutritio.activity.RecipeActivity;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.fragment.AbstractActionBarCallBack;
import com.example.abilambin.nutritio.fragment.MealActionBarCallBack;
import com.example.abilambin.nutritio.fragment.RecipeActionBarCallBack;

/**
 * Created by abilambin on 13/03/2018.
 */

public class RecipeViewHolder extends GenericViewHolder<Recipe> {

    public RecipeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Recipe recipe) {
        super.bind(recipe);

        TextView name = itemView.findViewById(R.id.name);
        name.setText(recipe.getName());

    }

    @Override
    public void onClick(View itemView) {
        Intent intent = new Intent(itemView.getContext(), RecipeActivity.class);

        // On appelle l'activité de visualisation de l'ingrédient concerné
        intent.putExtra("recipe", getElement());
        itemView.getContext().startActivity(intent);
    }

    @Override
    public AbstractActionBarCallBack getActionBarCallBack() {
        RecipeActionBarCallBack bar = new RecipeActionBarCallBack();
        bar.setSelectedEntry(getElement());
        bar.setContext(itemView.getContext());
        return bar;
    }
}
