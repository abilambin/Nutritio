package com.example.abilambin.nutritio.fragment;

import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.ingredientList.ScoredRecipe;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.specific.ScoredRecipeRestCaller;
import com.example.abilambin.nutritio.utils.PersonSession;
import com.example.abilambin.nutritio.utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;

public class RecipeFragment extends AbstractListFragment<ScoredRecipe> {

    @BindView(R.id.title)
    protected TextView title;

    ScoredRecipeRestCaller recipeRestCaller = new ScoredRecipeRestCaller();

    @Override
    public String getTitle() {
        return "Recettes";
    }

    @Override
    protected int getItem() {
        return R.layout.item_recipe;
    }

    @Override
    protected int getCurrentFragment() {
        return -1;
    }

    @Override
    protected int getListLayout() {
        return R.layout.list_recipe;
    }

    @Override
    public List<ScoredRecipe> getList(){
        try {
            PersonSession session = PersonSession.getInstance();
            List<ScoredRecipe> list = null;

            //On récupère la liste des ingrédients récupéré par appel rest
            if(session.getRecipe() == null) {
                list = recipeRestCaller.getScoredRecipe(Utils.getUserId(this.getActivity()));
                session.setRecipe(list);

                //Si elle est null, alors on en crée une vide
                if (list == null) return new ArrayList<>();
            }else{
                list = session.getRecipe();
            }

            // Tri la liste pas score décroissant
            list.sort(new Comparator<ScoredRecipe>() {
                @Override
                public int compare(ScoredRecipe recipe, ScoredRecipe t1) {
                    return t1.getScore() - recipe.getScore();
                }
            });

            return list;

        } catch (WebServiceCallException | ExecutionException | CannotAuthenticateUserException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return new ArrayList<>();
        }
    }
}
