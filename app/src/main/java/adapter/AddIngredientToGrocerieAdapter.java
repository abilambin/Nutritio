package adapter;

import android.content.Context;

import com.example.abilambin.nutritio.activity.CreateIngredientEntryGrocerieActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.ingredientList.IngredientList;

import java.util.List;

/**
 * Created by serial on 09/03/2018.
 */

public class AddIngredientToGrocerieAdapter extends AddIngredientToListAdapter<IngredientList> {
    public AddIngredientToGrocerieAdapter(Context context, List<Ingredient> items) {
        super(context, items);
    }

    @Override
    public Class getCreateActivity() {
        return CreateIngredientEntryGrocerieActivity.class;
    }
}
