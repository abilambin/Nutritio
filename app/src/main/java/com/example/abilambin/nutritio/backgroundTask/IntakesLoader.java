package com.example.abilambin.nutritio.backgroundTask;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abilambin.nutritio.bdd.model.Goal;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.restApi.AuthenticateUser;
import com.example.abilambin.nutritio.restApi.RestCallerConstant;
import com.example.abilambin.nutritio.utils.Intakes;
import com.example.abilambin.nutritio.utils.PersonSession;
import com.example.abilambin.nutritio.utils.PersonalGoal;
import com.example.abilambin.nutritio.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class IntakesLoader extends AsyncTask<Integer, Void, Intakes> {
    private int userId;

    // Intakes
    private ProgressBar proteinesProgressBar;
    private TextView proteinesPctTextView;
    private ProgressBar glucidesProgressBar;
    private TextView glucidesPctTextView;
    private ProgressBar sucreProgressBar;
    private ProgressBar lipidesProgressBar;
    private TextView lipidesPctTextView;
    private ProgressBar agsProgressBar;
    private ProgressBar fibresProgressBar;
    private TextView fibresPctTextView;
    private static final int INTAKES_MODE = 0;

    // Energy
    private CircularProgressBar circularProgressBar;
    private TextView energyProgressText;
    private static final int ENERGY_MODE = 1;

    private int mode;


    public IntakesLoader(Activity activity, ProgressBar proteinesProgressBar, TextView proteinesPctTextView, ProgressBar glucidesProgressBar, TextView glucidesPctTextView, ProgressBar sucreProgressBar, ProgressBar lipidesProgressBar, TextView lipidesPctTextView, ProgressBar agsProgressBar, ProgressBar fibresProgressBar, TextView fibresPctTextView) {
        this.userId = Utils.getUserId(activity);
        this.proteinesProgressBar = proteinesProgressBar;
        this.proteinesPctTextView = proteinesPctTextView;
        this.glucidesProgressBar = glucidesProgressBar;
        this.glucidesPctTextView = glucidesPctTextView;
        this.sucreProgressBar = sucreProgressBar;
        this.lipidesProgressBar = lipidesProgressBar;
        this.lipidesPctTextView = lipidesPctTextView;
        this.agsProgressBar = agsProgressBar;
        this.fibresProgressBar = fibresProgressBar;
        this.fibresPctTextView = fibresPctTextView;
        this.mode = this.INTAKES_MODE;
    }

    public IntakesLoader(Activity activity, CircularProgressBar circularProgressBar, TextView energyProgressText){
        this.userId = Utils.getUserId(activity);
        this.circularProgressBar = circularProgressBar;
        this.energyProgressText = energyProgressText;
        this.mode = this.ENERGY_MODE;
    }

    @Override
    protected Intakes doInBackground(Integer... mode) {
        OkHttpClient httpClient;
        Response response = null;
        Intakes intakes = null;

        try {
            // Donnée utilisateur
            PersonSession session = PersonSession.getInstance();

            if(session.getGlobalIntake() == null) {
                Request getIntakes = new Request.Builder()
                        .url(RestCallerConstant.SERVER_ADDR + "/api/people/" + this.userId + "/getTodayIntakes")
                        .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                        .build();

                httpClient = new OkHttpClient();

                response = httpClient.newCall(getIntakes).execute();
                if (response.code() == 200) {
                    intakes = new Gson().fromJson(response.body().string(), new TypeToken<Intakes>() {}.getType());
                    session.setGlobalIntake(intakes);
                } else {
                    throw new NetworkErrorException("Unable to load user intakes information : " + response.code());
                }
            }else{
                intakes = session.getGlobalIntake();
            }
        } catch (IOException | NetworkErrorException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        }

        return intakes;
    }

    /**
     * Met a jour une progressBar avec son texte et ses infos associés
     * @param bar La progress bar a mettre a jour
     * @param view  Le texte a mettre a jour
     * @param val la progression
     * @param obj l'équivalent au 100%
     */
    private void showValueBar(ProgressBar bar, TextView view, int val, int obj) {
        bar.setProgress(Utils.percent(val, obj));
        if (view != null) view.setText(val + " / "+obj);
    }


    /**
     * affiche sur les barres de progression les valeurs associées aux différents intakes
     * @param intakes les intakes à afficher
     */
    @Override
    protected void onPostExecute(Intakes intakes) {
        super.onPostExecute(intakes);
        Goal goal = PersonalGoal.getInstance().getGoal();

        switch(this.mode){
            case INTAKES_MODE:
                showValueBar(proteinesProgressBar, proteinesPctTextView, intakes.getProtein(), goal.getProtein());

                showValueBar(glucidesProgressBar, glucidesPctTextView, intakes.getCarbohydrate(), goal.getCarbohydrate());

                showValueBar(sucreProgressBar, null, intakes.getSugar(), goal.getSugar());

                showValueBar(lipidesProgressBar, lipidesPctTextView, intakes.getFat(), goal.getFat());

                showValueBar(agsProgressBar, null, intakes.getSaturatedFat(), goal.getSaturatedFat());

                showValueBar(fibresProgressBar, fibresPctTextView, intakes.getFibre(), goal.getFibre());

                break;

            case ENERGY_MODE:
                int eneryPercent = Utils.percent(intakes.getEnergy(), goal.getEnergy());
                energyProgressText.setText(intakes.getEnergy() + " kcal");
                circularProgressBar.setProgressWithAnimation(eneryPercent, 1500);
                break;

        }
    }
}
