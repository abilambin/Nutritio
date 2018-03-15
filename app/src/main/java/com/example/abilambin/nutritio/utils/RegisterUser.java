package com.example.abilambin.nutritio.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.abilambin.nutritio.R;
import com.example.abilambin.nutritio.exception.CannotCreateUserException;
import com.example.abilambin.nutritio.restApi.RestCallerConstant;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterUser extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private AppCompatActivity activity;
    private String email;
    private String firstname;
    private String lastname;
    private String password;

    public RegisterUser(Context context, AppCompatActivity activity, String email, String firstname, String lastname, String password) {
        this.context = context;
        this.activity = activity;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        // ################################################################
        // ##################### JHipster Account #########################
        // ################################################################

        JsonObject jhipsterAccount = new JsonObject();                          // JsonObject matérialisant le nouveau compte
        SimpleDateFormat dayDF = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hourDF = new SimpleDateFormat("hh:mm:ss");
        Date now = Calendar.getInstance().getTime();
        JsonArray authorities = new JsonArray();                                // Droit de l'utilisateur
        authorities.add("ROLE_USER");                                           //

        // Remplissage des informations du compte JHipster
        try {
            jhipsterAccount.addProperty("activated", true);
            jhipsterAccount.add("authorities", authorities);
            jhipsterAccount.addProperty("vreateBy", "Nutritio");
            jhipsterAccount.addProperty("createdDate", dayDF.format(now) + "T" + hourDF.format(now) + "Z");      // Le '+ "Z" ' sert à être reconnut en tant que java.time.Instant par le serveur
            jhipsterAccount.addProperty("email", email);
            jhipsterAccount.addProperty("firstname", firstname);
            jhipsterAccount.addProperty("lastname", lastname);
            jhipsterAccount.addProperty("login", email);
            jhipsterAccount.addProperty("password", password);
            jhipsterAccount.addProperty("imageUrl", "");
            jhipsterAccount.addProperty("langKey", "en");
            jhipsterAccount.addProperty("lastModifiedBy", "Nutritio");
            jhipsterAccount.addProperty("lastModifiedDate", dayDF.format(now) + "T" + hourDF.format(now) + "Z"); // Le '+ "Z" ' sert à être reconnut en tant que java.time.Instant par le serveur

            Request jhipsterCreateRequest = new Request.Builder()
                    .url(RestCallerConstant.SERVER_ADDR + "/api/register")
                    .post(RequestBody.create(RestCallerConstant.JSON_MEDIATYPE, new Gson().toJson(jhipsterAccount)))
                    .build();


            // ###################################################################
            // ######################## Nutritio Account #########################
            // ###################################################################

            // ######### Création de sa liste de course #########
            JsonObject grocerie = new JsonObject();
            grocerie.addProperty("name", email+"'s grocerie");
            grocerie.add("ingredientEntries", new JsonArray());

            // ######### Création de sa liste noire #############
            JsonObject blackList = new JsonObject();
            blackList.addProperty("name", email+"'s black list");
            blackList.add("ingredientEntries", new JsonArray());

            // ######### Création de son stock ##################
            JsonObject stock = new JsonObject();
            stock.addProperty("name", email+"'s stock");
            stock.add("ingredientEntries", new JsonArray());


            // Inscription JHipsiter
            OkHttpClient httpClient = new OkHttpClient();
            Response response = httpClient.newCall(jhipsterCreateRequest).execute();

            // Récupération de la réponse sous forme de JsonObject
            if (response.code() != 201) {
                JsonObject responseJson = new JsonParser().parse(response.body().string()).getAsJsonObject();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Une erreur est survenue lors de la création", Toast.LENGTH_SHORT).show();
                    }
                });
                throw new CannotCreateUserException(responseJson.get("title").toString());
            } else {
                // Suite de l'inscription Nutritio
                // Création des différents objets nécessaire à la création du compte nutritio
                Request createGrocerieRequest = new Request.Builder()
                        .url(RestCallerConstant.SERVER_ADDR + "/api/groceries")
                        .post(RequestBody.create(RestCallerConstant.JSON_MEDIATYPE, new Gson().toJson(grocerie)))
                        .build();
                // Requete HTTP
                response = httpClient.newCall(createGrocerieRequest).execute();
                // Vérification du code retour HTTP
                if(response.code() != 201){
                    JsonObject jsonResponse = new JsonParser().parse(response.body().string()).getAsJsonObject();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Une erreur est survenue lors de la création", Toast.LENGTH_SHORT).show();
                        }
                    });
                    throw new CannotCreateUserException(jsonResponse.get("title").toString());
                }
                grocerie = new JsonParser().parse(response.body().string()).getAsJsonObject();


                // Création des différents objets nécessaire à la création du compte nutritio
                Request createBlackListRequest = new Request.Builder()
                        .url(RestCallerConstant.SERVER_ADDR + "/api/black-lists")
                        .post(RequestBody.create(RestCallerConstant.JSON_MEDIATYPE, new Gson().toJson(blackList)))
                        .build();
                // Requete HTTP
                response = httpClient.newCall(createBlackListRequest).execute();
                // Vérification du code retour HTTP
                if(response.code() != 201){
                    JsonObject jsonResponse = new JsonParser().parse(response.body().string()).getAsJsonObject();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Une erreur est survenue lors de la création", Toast.LENGTH_SHORT).show();
                        }
                    });

                    throw new CannotCreateUserException(jsonResponse.get("title").toString());
                }
                blackList = new JsonParser().parse(response.body().string()).getAsJsonObject();



                // Création des différents objets nécessaire à la création du compte nutritio
                Request createStockRequest = new Request.Builder()
                        .url(RestCallerConstant.SERVER_ADDR + "/api/stocks")
                        .post(RequestBody.create(RestCallerConstant.JSON_MEDIATYPE, new Gson().toJson(stock)))
                        .build();
                // Requete HTTP
                response = httpClient.newCall(createStockRequest).execute();
                // Vérification du code retour HTTP
                if(response.code() != 201){
                    JsonObject jsonResponse = new JsonParser().parse(response.body().string()).getAsJsonObject();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Une erreur est survenue lors de la création", Toast.LENGTH_SHORT).show();
                        }
                    });
                    throw new CannotCreateUserException(jsonResponse.get("title").toString());
                }
                stock = new JsonParser().parse(response.body().string()).getAsJsonObject();



                // Creation du compte Nutritio
                JsonObject nutritioAccount = new JsonObject();
                nutritioAccount.addProperty("email", email);
                nutritioAccount.addProperty("firstname", firstname);
                nutritioAccount.addProperty("lastname", lastname);
                nutritioAccount.addProperty("birthday", dayDF.format(now) + "T" + hourDF.format(now) + "Z");
                blackList.remove("name");
                blackList.remove("ingredientEntries");
                grocerie.remove("name");
                grocerie.remove("ingredientEntries");
                stock.remove("name");
                stock.remove("ingredientEntries");
                nutritioAccount.add("blackList", blackList);
                nutritioAccount.add("grocerie", grocerie);
                nutritioAccount.add("stock", stock);

                Request createNutritioAccountRequest = new Request.Builder()
                        .url(RestCallerConstant.SERVER_ADDR + "/api/people")
                        .post(RequestBody.create(RestCallerConstant.JSON_MEDIATYPE, new Gson().toJson(nutritioAccount)))
                        .build();

                // Requete HTTP
                response = httpClient.newCall(createNutritioAccountRequest).execute();

                if(response.code() == 201){
                    // Tout a correctement été créé
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, R.string.account_created, Toast.LENGTH_SHORT).show();
                        }
                    });
                    activity.finish();
                    return true;
                }else{
                    JsonObject jsonResponse = new JsonParser().parse(response.body().string()).getAsJsonObject();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Une erreur est survenue lors de la création", Toast.LENGTH_SHORT).show();
                        }
                    });
                    throw new CannotCreateUserException(jsonResponse.get("title").toString());
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT);
        }

        return false;
    }
}
