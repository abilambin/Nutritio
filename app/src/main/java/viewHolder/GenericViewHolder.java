package viewHolder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Toast;

import com.example.abilambin.nutritio.activity.IngredientActivity;
import com.example.abilambin.nutritio.fragment.IngredientEntryActionBarCallBack;

import java.io.Serializable;

import static android.content.ContentValues.TAG;

/**
 * Created by abilambin on 12/03/2018.
 */

public class GenericViewHolder<T extends Serializable> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


    private ActionMode mActionMode;

    private T element;
    public GenericViewHolder(View itemView) {

        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void bind(T element) {
        this.element = element;
    }

    @Override
    public void onClick(View itemView) {

        Log.d(TAG, "onClick " + getItemId() + " " + itemView);

        Intent intent = new Intent(itemView.getContext(), IngredientActivity.class);

        // On appelle l'activité de visualisation de l'ingrédient concerné
        intent.putExtra("ingredient", element);
        //startActivity(intent);
    }

    @Override
    public boolean onLongClick(View itemView) {

        Log.d(TAG, "onLONGClick " + getItemId() + " " + itemView);

        Toast.makeText(itemView.getContext(), "Long press on position :"+getPosition(),
                Toast.LENGTH_LONG).show();


        itemView.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        //TODO : Changer le style de l'élément sélectionné
        itemView.setBackgroundColor(222222);

        return true;

    }

}
