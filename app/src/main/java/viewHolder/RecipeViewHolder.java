package viewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;

/**
 * Created by abilambin on 13/03/2018.
 */

public class RecipeViewHolder extends GenericViewHolder<Recipe> {

    public RecipeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Recipe recipe) {

        TextView name = itemView.findViewById(R.id.name);
        name.setText(recipe.getName());

    }
}
