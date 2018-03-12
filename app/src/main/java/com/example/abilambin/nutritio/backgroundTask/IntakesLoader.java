package com.example.abilambin.nutritio.backgroundTask;

import android.accounts.NetworkErrorException;
import android.os.AsyncTask;

import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.restApi.AuthenticateUser;
import com.example.abilambin.nutritio.restApi.RestCallerConstant;
import com.example.abilambin.nutritio.utils.Intakes;
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

    public IntakesLoader(int userId) {
        this.userId = userId;
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
                    Request getIntakes = new Request.Builder()
                            .url(RestCallerConstant.SERVER_ADDR + "/api/people/"+ this.userId +"/getTodayIntakes")
                            .header("Authorization", AuthenticateUser.getInstance().getAuthToken())
                            .build();

                    httpClient = new OkHttpClient();

                    response = httpClient.newCall(getIntakes).execute();
                    if(response.code() == 200){
                        intakes = new Gson().fromJson(response.body().string(), new TypeToken<Intakes>(){}.getType());
                    }else{
                        throw new NetworkErrorException("Unable to load user intakes information : " + response.code());
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
}
