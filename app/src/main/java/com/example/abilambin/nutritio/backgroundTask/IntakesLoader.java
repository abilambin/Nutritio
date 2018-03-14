package com.example.abilambin.nutritio.backgroundTask;

import android.accounts.NetworkErrorException;
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

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bellamy on 08/03/18.
 */

public class IntakesLoader extends AsyncTask<Integer, Void, Intakes> {
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

    public IntakesLoader(int userId, ProgressBar proteinesProgressBar, TextView proteinesPctTextView, ProgressBar glucidesProgressBar, TextView glucidesPctTextView, ProgressBar sucreProgressBar, ProgressBar lipidesProgressBar, TextView lipidesPctTextView, ProgressBar agsProgressBar, ProgressBar fibresProgressBar, TextView fibresPctTextView) {
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
    protected Intakes doInBackground(Integer... mode) {
        OkHttpClient httpClient;
        Response response = null;
        Intakes intakes = null;

        try {
            switch(mode[0]){
                case 0:     // Mode ingredient
                    /*Request getIngredient = new Request.Builder()
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
                    }*/
                    break;
                case 1:     // Mode plat
                    /*Request getRecipe = new Request.Builder()
                            .url(RestCallerConstant.SERVER_ADDR + "/api/recipes/" + mode[1])
                            .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                            .build();

                    response = httpClient.newCall(getRecipe).execute();
                    if(response.code() == 200) {
                        JsonObject recipe = new JsonParser().parse(response.body().string()).getAsJsonObject();
                        ingredients.addAll(recipe.get("ingredientEntries").getAsJsonArray());
                    }else{
                        throw new NetworkErrorException("Unable to load recipe intakes information : " + response.code());
                    }*/
                    break;
                case 2 :    // Mode repas
                    /*Request getMeal = new Request.Builder()
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
                    }*/
                    break;
                case 3:     // Mode tous les repas
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
                    break;
            }
        } catch (IOException | NetworkErrorException | CannotAuthenticateUserException e) {
            e.printStackTrace();
        }

        //System.out.println(ingredients.toString());
        //Gson gson = new Gson();


        //List<Ingredient> res = new ArrayList<>();
        // Pour chaque ingredient
        /*for (JsonElement ingredientEntry : ingredients) {
            int amount = ingredientEntry.getAsJsonObject().get("amount").getAsInt();
            JsonObject ingredient = ingredientEntry.getAsJsonObject().get("ingredient").getAsJsonObject();
            res.add((Ingredient) gson.fromJson(ingredient.toString(), new TypeToken<Ingredient>(){}.getType()));
            /*totalProteineNeeds += amount * ingredient.get("protein").getAsInt() / 100;                          //
            totalGlucideNeeds += amount * ingredient.get("carbohydrate").getAsInt() / 100;                      //
            totalLipideNeeds += amount * ingredient.get("fat").getAsInt() / 100;                                //
            totalSucreNeeds += amount * ingredient.get("sugar").getAsInt() / 100;                               // Divisé par 100 puique les valeurs nutritives sont notées pour 100 unités
            totalFibreNeeds += amount * ingredient.get("fibre").getAsInt() / 100;                               //
            totalAgsNeeds += amount * ingredient.get("saturatedFat").getAsInt() / 100;                          //
            */
        //}
        return intakes;
    }

    @Override
    protected void onPostExecute(Intakes intakes) {
        super.onPostExecute(intakes);
        Goal goal = PersonalGoal.getInstance().getGoal();

        proteinesProgressBar.setProgress(Utils.percent(intakes.getProtein() / 100, goal.getProtein()));
        proteinesPctTextView.setText(intakes.getProtein()  / 100+" / "+goal.getProtein());

        glucidesProgressBar.setProgress(Utils.percent(intakes.getCarbohydrate()  / 100, 100));
        glucidesPctTextView.setText(intakes.getCarbohydrate()  / 100+" / "+goal.getCarbohydrate());

        sucreProgressBar.setProgress(Utils.percent(intakes.getSugar()  / 100, 100));

        lipidesProgressBar.setProgress(Utils.percent(intakes.getFat()  / 100, 100));
        lipidesPctTextView.setText(intakes.getFat()  / 100+" / "+goal.getFat());

        agsProgressBar.setProgress(Utils.percent(intakes.getSaturatedFat()  / 100, 100));

        fibresProgressBar.setProgress(Utils.percent(intakes.getFibre()  / 100, 100));
        fibresPctTextView.setText(intakes.getFibre()  / 100+" / "+goal.getFibre());
    }
}
