package com.example.abilambin.nutritio.fragment;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.design.widget.FloatingActionButton;
        import android.util.Log;
        import android.view.ActionMode;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.example.abilambin.nutritio.R;
        import com.example.abilambin.nutritio.activity.RecipeActivity;
        import com.example.abilambin.nutritio.bdd.model.Meal;
        import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
        import com.example.abilambin.nutritio.exception.WebServiceCallException;
        import com.example.abilambin.nutritio.restApi.specific.MealRestCaller;

        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        import java.util.concurrent.ExecutionException;

        import butterknife.BindView;

public class MealListFragment extends AbstractListFragment<Meal> {

    LinearLayout ll;

    @BindView(R.id.mealListFragmentTitle)
    protected TextView title;

    MealRestCaller mealRestCaller = new MealRestCaller();

    private ActionMode mActionMode;

    @BindView(R.id.addRecipeToMealButton)
    FloatingActionButton addRecipeToMealButton;


    @Override
    protected int getListLayout() {
        return R.layout.fragment_meal_list;
    }

    @Override
    public List<Meal> getList(){
        try {
            //On récupère la liste des ingrédients récupéré par appel rest
            List<Meal> list = (List<Meal>) mealRestCaller.getAll();

            //Si elle est null, alors on en crée une vide
            if (list == null) return new ArrayList<>();

            return list;

        } catch (WebServiceCallException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (InterruptedException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ExecutionException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (CannotAuthenticateUserException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrédients :");
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    /**
     * Génère la vue de l'ingrédient en paramètre (y ajoute les listener d'évênements)
     * @param meal le plat à afficher
     * @param inflater
     * @return
     */
    @Override
    protected View createElementView(final Meal meal, LayoutInflater inflater) {
        View vi = inflater.inflate(R.layout.list_ingredient, null);


        if (meal.getDate() != null) {
            Log.d("MealListFragment TEST :",meal.getDate().getHours()+"");
            TextView hourMealTV = vi.findViewById(R.id.mealItemHourTextView);
            Date mealDate = meal.getDate();

            String s = formate(mealDate);
            hourMealTV.setText(s);
        }

        /*
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), AddIngredientToListActivity.class);
                startActivity(intent);
            }
        });*/

        /*final Ingredient ingredient = entry.getIngredient();
        String brand = ingredient.getBrand();
        brand = (brand == null)?"":brand+", ";

        TextView ingredientName = vi.findViewById(R.id.list_ingredient_name);
        TextView ingredientBrand = vi.findViewById(R.id.list_ingredient_brand);
        TextView ingredientQuantity = vi.findViewById(R.id.list_ingredient_quantity);

        ingredientName.setText(Html.fromHtml("<b>"+ingredient.getName()+"</b>"));
        ingredientBrand.setText(brand);
        ingredientQuantity.setText(entry.getAmount() + entry.getUnitSmallText());


        // On récupère le layout de l'ingrédient
        ll = vi.findViewById(R.id.ingredientContainer);

        // ON CLICK -> READ
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IngredientActivity.class);

                // On appelle l'activité de visualisation de l'ingrédient concerné
                intent.putExtra("ingredient", ingredient);
                startActivity(intent);

            }
        });

        // ON LONG CLICK -> UPDATE, DELETE
        ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Génération d'un retour haptique
                ll.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                //TODO : Changer le style de l'élément sélectionné
                ll.setBackgroundColor(222222);


                // On génère la barre de modification de l'ingrédient
                ActionBarCallBack bar = new ActionBarCallBack();

                // On ajoute l'id de la vue de l'ingrédient à la barre
                bar.setSelectedItemId(v.getId());
                bar.setSelectedEntry(entry);

                mActionMode = getActivity().startActionMode(bar);
                Object[] tags = new Object[2];
                tags[0] = v.getRootView().getContext();
                mActionMode.setTag(tags);
                return true;
            }
        });*/

        TextView nameMealTV = vi.findViewById(R.id.mealItemNameTextView);
        nameMealTV.setText(meal.getName());

        Bundle bundle = getArguments();
        if (bundle != null) {
            title.setText(bundle.getString("title"));
        }


        ll = vi.findViewById(R.id.linearLayout);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecipeActivity.class);

                intent.putExtra("name", meal.getName());
                intent.putExtra("description", meal.getRecipe());
                startActivity(intent);

            }
        });

        return vi;
    }

    @NonNull
    private String formate(Date mealDate) {
        String s;

        int h = mealDate.getHours();
        int m = mealDate.getMinutes();

        s = ((h < 10)?"0":"") + h + "H" + ((m < 10)?"0":"") + m;

        return s;
    }

    public void onStop() {
        if (mActionMode != null) mActionMode.finish();

        super.onStop();
    }




}
