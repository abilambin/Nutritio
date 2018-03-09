package adapter;

import android.content.Context;

import com.example.abilambin.nutritio.activity.CreateIngredientEntryStockActivity;
import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.bdd.model.ingredientList.IngredientList;

import java.util.List;

/**
 * Created by serial on 09/03/2018.
 */

public class AddIngredientToStockAdapter extends AddIngredientToListAdapter<IngredientList>{
    public AddIngredientToStockAdapter(Context context, List<Ingredient> items) {
        super(context, items);
    }

    @Override
    public Class getCreateActivity() {
        return CreateIngredientEntryStockActivity.class;
    }
}
