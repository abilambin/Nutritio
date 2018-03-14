package viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.fragment.AbstractActionBarCallBack;
import com.example.abilambin.nutritio.fragment.MealActionBarCallBack;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by abilambin on 13/03/2018.
 */

public class MealViewHolder extends GenericViewHolder<Meal> {

    public MealViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Meal meal) {
        super.bind(meal);


        TextView hour = itemView.findViewById(R.id.mealHour);
        hour.setText(formate(meal.getDate()));

        TextView name = itemView.findViewById(R.id.mealName);
        name.setText(meal.getName());

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
