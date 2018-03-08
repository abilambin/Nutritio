package com.example.abilambin.nutritio.backgroundTask;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.restApi.AuthenticateUser;
import com.example.abilambin.nutritio.restApi.RestCallerConstant;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bellamy on 08/03/18.
 */

public class IntakesLoader extends AsyncTask<Integer, Void, Void> {
    private int userId;
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

    private final long proteineNeeds = 70000;
    private final long glucideNeeds = 124000;
    private final long lipideNeeds = 62000;
    private final long sucreNeeds = 30000;
    private final long fibreNeeds = 30000;
    private final long agsNeeds = 10000;

    private Activity activity;

    public IntakesLoader(Activity activity, int userId, ProgressBar proteinesProgressBar, TextView proteinesPctTextView, ProgressBar glucidesProgressBar, TextView glucidesPctTextView, ProgressBar sucreProgressBar, ProgressBar lipidesProgressBar, TextView lipidesPctTextView, ProgressBar agsProgressBar, ProgressBar fibresProgressBar, TextView fibresPctTextView) {
        this.activity = activity;
        this.userId = userId;
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
    }

    @Override
    protected Void doInBackground(Integer... mode) {
        OkHttpClient httpClient = new OkHttpClient();
        Response response = null;
        JsonArray ingredients = new JsonArray();
        long totalProteineNeeds = 0;
        long totalGlucideNeeds = 0;
        long totalLipideNeeds = 0;
        long totalSucreNeeds = 0;
        long totalFibreNeeds = 0;
        long totalAgsNeeds = 0;
        try {
            switch(mode[0]){
                case 0:     // Mode ingredient
                    Request getIngredient = new Request.Builder()
                            .url(RestCallerConstant.SERVER_ADDR + "/api/ingredients/" + mode[1])
                            .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                            .build();

                    response = httpClient.newCall(getIngredient).execute();
                    if(response.code() == 200) {
                        JsonObject ingredient = new JsonParser().parse(response.body().string()).getAsJsonObject();
                        ingredients = new JsonArray();
                        ingredients.add(ingredient);
                    }else{
                        throw new NetworkErrorException("Unable to load ingredient intakes information : " + response.code());
                    }
                    break;
                case 1:     // Mode plat
                    Request getRecipe = new Request.Builder()
                            .url(RestCallerConstant.SERVER_ADDR + "/api/recipes/" + mode[1])
                            .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                            .build();

                    response = httpClient.newCall(getRecipe).execute();
                    if(response.code() == 200) {
                        JsonObject recipe = new JsonParser().parse(response.body().string()).getAsJsonObject();
                        ingredients.addAll(recipe.get("ingredientEntries").getAsJsonArray());
                    }else{
                        throw new NetworkErrorException("Unable to load recipe intakes information : " + response.code());
                    }
                    break;
                case 2 :    // Mode repas
                    Request getMeal = new Request.Builder()
                            .url(RestCallerConstant.SERVER_ADDR + "/api/mealsOf/" + this.userId)
                            .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                            .build();

                    httpClient = new OkHttpClient();

                    response = httpClient.newCall(getMeal).execute();
                    if(response.code() == 200){
                        JsonObject meal = new JsonParser().parse(response.body().string()).getAsJsonObject();
                        JsonArray recipes = meal.getAsJsonObject().get("recipes").getAsJsonArray();

                        // Pour chaque plat
                        for (JsonElement recipe : recipes) {
                            Request getIngredients = new Request.Builder()
                                    .url(RestCallerConstant.SERVER_ADDR + "/api/recipes/" + recipe.getAsJsonObject().get("id").getAsString())
                                    .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                                    .build();
                            response = httpClient.newCall(getIngredients).execute();

                            ingredients = new JsonParser().parse(response.body().string()).getAsJsonObject().get("ingredientEntries").getAsJsonArray();

                        }


                    }else{
                        throw new NetworkErrorException("Unable to load user intakes information : " + response.code());
                    }
                    break;
                case 3:     // Mode tous les repas
                    Request getUserMeals = new Request.Builder()
                            .url(RestCallerConstant.SERVER_ADDR + "/api/mealsOf/" + this.userId)
                            .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                            .build();

                    httpClient = new OkHttpClient();

                    response = httpClient.newCall(getUserMeals).execute();
                    if(response.code() == 200){
                        JsonArray meals = new JsonParser().parse(response.body().string()).getAsJsonArray();


                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        // Pour chaque repas
                        for (JsonElement meal : meals) {
                            String now = formatter.format(new Date());
                            String mealDate = meal.getAsJsonObject().get("date").getAsString().split("T")[0];

                            // Si c'est la date du jour
                            if(now.equals(mealDate)) {
                                JsonArray recipes = meal.getAsJsonObject().get("recipes").getAsJsonArray();

                                // Pour chaque plat
                                for (JsonElement recipe : recipes) {
                                    Request getIngredients = new Request.Builder()
                                            .url(RestCallerConstant.SERVER_ADDR + "/api/recipes/" + recipe.getAsJsonObject().get("id").getAsString())
                                            .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                                            .build();
                                    response = httpClient.newCall(getIngredients).execute();

                                    ingredients = new JsonParser().parse(response.body().string()).getAsJsonObject().get("ingredientEntries").getAsJsonArray();

                                }
                            }
                        }

                    }else{
                        throw new NetworkErrorException("Unable to load user intakes information : " + response.code());
                    }
                    break;
            }
        } catch (IOException | NetworkErrorException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        }

        // Pour chaque ingredient
        for (JsonElement ingredientEntry : ingredients) {
            int amount = ingredientEntry.getAsJsonObject().get("amount").getAsInt();
            JsonObject ingredient = ingredientEntry.getAsJsonObject().get("ingredient").getAsJsonObject();
            totalProteineNeeds += amount * ingredient.get("protein").getAsInt() / 100;                          //
            totalGlucideNeeds += amount * ingredient.get("carbohydrate").getAsInt() / 100;                      //
            totalLipideNeeds += amount * ingredient.get("fat").getAsInt() / 100;                                //
            totalSucreNeeds += amount * ingredient.get("sugar").getAsInt() / 100;                               // Divisé par 100 puique les valeurs nutritives sont notées pour 100 unités
            totalFibreNeeds += amount * ingredient.get("fibre").getAsInt() / 100;                               //
            totalAgsNeeds += amount * ingredient.get("saturatedFat").getAsInt() / 100;                          //
        }

        // Calcul des pourcentages journalier
        final int pctProtein = (int) (totalProteineNeeds * 100 / proteineNeeds);
        final int pctGlucide = (int) (totalGlucideNeeds * 100 / glucideNeeds);
        final int pctLipide = (int) (totalLipideNeeds * 100 / lipideNeeds);
        final int pctSucre = (int) (totalSucreNeeds * 100 / sucreNeeds);
        final int pctFibre = (int) (totalFibreNeeds * 100 / fibreNeeds);
        final int pctAgs = (int) (totalAgsNeeds * 100 / agsNeeds);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                proteinesProgressBar.setProgress(pctProtein);
                proteinesPctTextView.setText(pctProtein + "%");

                glucidesProgressBar.setProgress(pctGlucide);
                glucidesPctTextView.setText(pctGlucide + "%");
                sucreProgressBar.setProgress(pctSucre);

                lipidesProgressBar.setProgress(pctLipide);
                lipidesPctTextView.setText(pctLipide + "%");
                agsProgressBar.setProgress(pctAgs);

                fibresProgressBar.setProgress(pctFibre);
                fibresPctTextView.setText(pctFibre + "%");
            }
        });

        return null;
    }
}
