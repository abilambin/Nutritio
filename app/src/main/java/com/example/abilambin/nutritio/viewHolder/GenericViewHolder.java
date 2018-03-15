package com.example.abilambin.nutritio.viewHolder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.LinearLayout;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.fragment.AbstractActionBarCallBack;

import java.io.Serializable;

/**
 * Created by abilambin on 12/03/2018.
 */

public abstract class GenericViewHolder<T extends Serializable> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private T element;

    Integer selectedItem;
    Integer position;

    LinearLayout layout;
    private ActionMode mActionMode;

    public void selectItem(Integer item) {
        if (selectedItem == null || !selectedItem.equals(item)) {
            selectedItem = item;
            layout.setBackgroundColor(Color.LTGRAY);
        } else {
            selectedItem = null;
            layout.setBackgroundColor(Color.WHITE);
        }
    }

    public T getElement(){
        return element;
    }

    public GenericViewHolder(View itemView) {

        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        layout = itemView.findViewById(R.id.linearLayout);
    }

    public void bind(T element) {
        this.element = element;
    }

    @Override
    public abstract void onClick(View itemView);

    public abstract AbstractActionBarCallBack getActionBarCallBack();
    
    @Override
    public boolean onLongClick(View itemView) {

        itemView.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        selectItem(position);
        
        ActionMode.Callback bar = getActionBarCallBack();

        mActionMode = itemView.startActionMode(bar);

        Object[] tags = new Object[2];
        tags[0] = itemView.getContext();
        mActionMode.setTag(tags);
        itemView.setSelected(true);

        return true;
    }


    public void setPosition(int position) {
        this.position = position;
    }
}
