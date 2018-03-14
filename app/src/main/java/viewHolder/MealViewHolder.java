package viewHolder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Recipe;
import com.example.abilambin.nutritio.fragment.AbstractActionBarCallBack;
import com.example.abilambin.nutritio.fragment.MealActionBarCallBack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import adapter.GenericAdapter;
import butterknife.ButterKnife;

/**
 * Created by abilambin on 13/03/2018.
 */

public class MealViewHolder extends GenericViewHolder<Meal> {

    private RecyclerView recyclerView;

    private List<Recipe> recipes;

    private boolean isExtended;

    public MealViewHolder(View itemView) {
        super(itemView);
        this.isExtended = false;
    }

    @Override
    public void bind(Meal meal) {
        super.bind(meal);

        this.recipes = meal.getRecipes();

        TextView hour = itemView.findViewById(R.id.mealHour);
        hour.setText(formate(meal.getDate()));

        TextView name = itemView.findViewById(R.id.mealName);
        name.setText(meal.getName());

    }

    @Override
    public void onClick(View itemView) {


        recyclerView = itemView.findViewById(R.id.recyclerView);

        if (!isExtended) {
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            recyclerView.setAdapter(new GenericAdapter<>(recipes, R.layout.item_recipe));
            isExtended = true;
        } else {
            recyclerView.setAdapter(null);
            isExtended = false;
        }

    }

    @Override
    public AbstractActionBarCallBack getActionBarCallBack() {
        MealActionBarCallBack bar = new MealActionBarCallBack();
        bar.setSelectedEntry(getElement());
        bar.setContext(itemView.getContext());
        return bar;
    }


    @NonNull
    private String formate(Date mealDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        return dateFormat.format(mealDate);
    }
}
