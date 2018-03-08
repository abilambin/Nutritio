package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serial on 08/03/2018.
 */

public abstract class AbstractListAdapter<E> extends BaseAdapter implements Filterable {
    private List<E> originalsElements; //original values

    public List<E> getFilteredElements() {
        return filteredElements;
    }

    private List<E> filteredElements; //displayed values

    public LayoutInflater getInflater() {
        return inflater;
    }

    private LayoutInflater inflater;

    public AbstractListAdapter(Context context, List<E> items){
        originalsElements = items;
        filteredElements = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filteredElements.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public abstract boolean filterCondition(E element, CharSequence constraint);

    @Override
    public abstract View getView(final int position, View convertView, ViewGroup viewGroup);

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<E> filteredArrList = new ArrayList<>();
                if(originalsElements == null){
                    originalsElements = new ArrayList<>(filteredElements);
                }
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = originalsElements.size();
                    results.values = originalsElements;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < originalsElements.size(); i++) {
                        E element = originalsElements.get(i);
                        boolean ok = filterCondition(element, constraint);

                        if (ok) {
                            filteredArrList.add(element);
                        }
                    }
                    // set the Filtered result to return
                    results.count = filteredArrList.size();
                    results.values = filteredArrList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredElements = (List<E>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
