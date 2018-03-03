package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Meal;

import java.util.List;

/**
 * Created by serial on 25/01/2018.
 */

public class MealArrayAdapter extends ArrayAdapter<Meal> {

    private List<Meal> objects;


    public MealArrayAdapter(Context context, List<Meal> objects) {
        super(context, 0, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_meal, null);
        }

        Meal m = objects.get(position);

        //construct view
        //nom
        TextView nameTextView = v.findViewById(R.id.mealItemNameTextView);
        nameTextView.setText(m.getName());

        String MINUTES = " minutes";
        //temps de préparation
        TextView preparationTimeTextView = v.findViewById(R.id.mealItemPreparationTimeTextView);
        preparationTimeTextView.setText(m.getPreparationTime() + MINUTES);

        //temps cuisson
        TextView cuissonTimeTextView = v.findViewById(R.id.mealItemCuissonTimeTextView);
        cuissonTimeTextView.setText(m.getBakingTime() + MINUTES);

        return v;


    }
}
