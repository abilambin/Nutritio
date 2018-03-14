package com.example.abilambin.nutritio.fragment;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.bdd.model.Meal;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.specific.MealRestCaller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import viewHolder.GenericViewHolder;
import viewHolder.MealViewHolder;

public class MealListFragment extends AbstractListFragment<Meal> {


    private Date debut;
    private Date fin;

    @BindView(R.id.title)
    protected TextView title;

    MealRestCaller mealRestCaller = new MealRestCaller();

    MealViewHolder mealViewHolder;



    public MealViewHolder getMealViewHolder() {
        return mealViewHolder;
    }

    private int item = R.layout.item_meal;


    @Override
    protected int getItem() { return R.layout.item_meal; }

    @Override
    protected int getListLayout() {
        return R.layout.list_meal;
    }

    @Override
    public List<Meal> getList(){
        try {

            initDates(debut, fin);

            List<Meal> list = (List<Meal>) mealRestCaller.getAllOf(37);
            //Between(37, debut, fin);

            //Si elle est null, alors on en crée une vide
            if (list == null) return new ArrayList<>();

            // On trie les repas par date
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                list.sort(new Comparator<Meal>() {
                    @Override
                    public int compare(Meal m1, Meal m2) {
                        return m1.getDate().compareTo(m2.getDate());
                    }
                });
            }


            return list;

        } catch (WebServiceCallException e) {
            System.out.println("##### ERROR - Impossible de récupérer les plats :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (InterruptedException e) {
            System.out.println("##### ERROR - Impossible de récupérer les plats :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ExecutionException e) {
            System.out.println("##### ERROR - Impossible de récupérer les plats :");
            e.printStackTrace();
            return new ArrayList<>();
        } catch (CannotAuthenticateUserException e) {
            System.out.println("##### ERROR - Impossible de récupérer les ingrplatsédients :");
            e.printStackTrace();
            return new ArrayList<>();
        }

    }


    public void initDates(Date start, Date end) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(start);

        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        this.debut = calendar.getTime();

        //Si une fin est définie, on la set, sinon, on la set au lendemain du début
        if (end != null) calendar.setTime(end);
        else calendar.add(Calendar.DAY_OF_MONTH, 1);

        this.fin = calendar.getTime();
    }


}
