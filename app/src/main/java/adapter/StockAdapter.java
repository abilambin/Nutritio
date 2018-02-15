package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abilambin.nutritio.bdd.model.Ingredient;

import java.util.List;

/**
 * Created by abilambin on 02/02/2018.
 */

public class StockAdapter  extends RecyclerView.Adapter<StockAdapter.MyViewHolder> {
        private List<Ingredient> ingredients;

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public MyViewHolder(View view) {
                super(view);
                //textView = view.findViewById(R.id.problemView);

            }
        }


        public StockAdapter(List<Ingredient> problems){
            this.ingredients = problems;
        }


        @Override
        public StockAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
            // Cree une view, charge le template
            //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.problem_row, parent, false);

            //MyViewHolder vh = new MyViewHolder(view);
            //return vh;
            return null;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //holder.textView.setText(ingredients.get(position).getType());
        }

        @Override
        public int getItemCount() {
            return this.ingredients.size();
        }

        public Ingredient getItem(int position) {
            return ingredients.get(position);
        }



}
