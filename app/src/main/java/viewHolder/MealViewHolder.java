package viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Meal;

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
        TextView hour = itemView.findViewById(R.id.mealHour);
        hour.setText(formate(meal.getDate()));

        TextView name = itemView.findViewById(R.id.mealName);
        name.setText(meal.getName());

    }



    @NonNull
    private String formate(Date mealDate) {
        String s;

        int h = mealDate.getHours();
        int m = mealDate.getMinutes();

        s = ((h < 10)?"0":"") + h + "H" + ((m < 10)?"0":"") + m;

        return s;
    }
}
