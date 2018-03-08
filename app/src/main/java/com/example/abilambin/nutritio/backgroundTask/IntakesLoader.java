package com.example.abilambin.nutritio.backgroundTask;

import android.accounts.NetworkErrorException;
import android.content.SharedPreferences;
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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.abilambin.nutritio.activity.LoginActivity.APP_INFO_NAME;

/**
 * Created by bellamy on 08/03/18.
 */

public class IntakesLoader extends AsyncTask<Void, Void, Void> {
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
    private ProgressBar selProgressBar;
    private TextView selPctTextView;

    private final int proteineNeeds = 70000;
    private final int glucideNeeds = 124000;
    private final int lipideNeeds = 62000;
    private final int sucreNeeds = 30000;
    private final int fibreNeeds = 30000;
    private final int agsNeeds = 10000;

    public IntakesLoader(int userId, ProgressBar proteinesProgressBar, TextView proteinesPctTextView, ProgressBar glucidesProgressBar, TextView glucidesPctTextView, ProgressBar sucreProgressBar, ProgressBar lipidesProgressBar, TextView lipidesPctTextView, ProgressBar agsProgressBar, ProgressBar fibresProgressBar, TextView fibresPctTextView, ProgressBar selProgressBar, TextView selPctTextView) {
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
        this.selProgressBar = selProgressBar;
        this.selPctTextView = selPctTextView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Request getUserMeals = new Request.Builder()
                        .url(RestCallerConstant.SERVER_ADDR + "/api/mealsOf/" + this.userId)
                        .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                        .build();

            OkHttpClient httpClient = new OkHttpClient();

            Response response = httpClient.newCall(getUserMeals).execute();
            if(response.code() == 200){
                JsonArray meals = new JsonParser().parse(response.body().string()).getAsJsonArray();
                int totalProteineNeeds = 0;
                int totalGlucideNeeds = 0;
                int totalLipideNeeds = 0;
                int totalSucreNeeds = 0;
                int totalFibreNeeds = 0;
                int totalAgsNeeds = 0;

                // Pour chaque repas
                for (JsonElement meal : meals) {
                    JsonArray recipes = meal.getAsJsonObject().get("recipes").getAsJsonArray();

                    // Pour chaque plat
                    for (JsonElement recipe : recipes) {
                        Request getIngredient = new Request.Builder()
                                .url(RestCallerConstant.SERVER_ADDR + "/api/recipes/" + recipe.getAsJsonObject().get("id").getAsString())
                                .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                                .build();
                        response = httpClient.newCall(getIngredient).execute();

                        JsonArray ingredients = new JsonParser().parse(response.body().string()).getAsJsonObject().get("ingredientEntries").getAsJsonArray();

                        for (JsonElement ingredient : ingredients) {
                            totalProteineNeeds += ingredient.getAsJsonObject().get("protein").getAsInt();
                            totalGlucideNeeds += ingredient.getAsJsonObject().get("carbohydrate").getAsInt();
                            totalLipideNeeds += ingredient.getAsJsonObject().get("fat").getAsInt();
                            totalSucreNeeds += ingredient.getAsJsonObject().get("sugar").getAsInt();
                            totalFibreNeeds += ingredient.getAsJsonObject().get("fibre").getAsInt();
                            totalAgsNeeds += ingredient.getAsJsonObject().get("saturatedFat").getAsInt();
                        }

                    }
                }

                proteinesProgressBar.setProgress(totalProteineNeeds * 100 / proteineNeeds);

                System.out.println(this.userId);
                System.out.println("#############");
                System.out.println(response.body().string());
                System.out.println("#############");
            }else{
                throw new NetworkErrorException("Unable to load user intakes information : " + response.code());
            }
        } catch (IOException | NetworkErrorException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        }


        return null;
    }
}
