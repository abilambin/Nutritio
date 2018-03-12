package viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abilambin on 12/03/2018.
 */

public class GenericViewHolder<T> extends RecyclerView.ViewHolder {

    public GenericViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(T element) {

    }

}
