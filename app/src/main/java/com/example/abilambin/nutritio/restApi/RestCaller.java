package com.example.abilambin.nutritio.restApi;

import android.provider.MediaStore;

import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bellamy on 15/02/18.
 */

public class RestCaller <T> implements RestCallerInterface<T>{
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String path;
    private TypeToken<List<T>> typeToken;

    public RestCaller(String itemName,  TypeToken<List<T>> typeToken){
        this.typeToken = typeToken;
        this.path = RestCallerConstant.SERVER_ADDR + "/api/" + itemName;
    }

    @Override
    public List<T> getAll() {
        List <T> res = new ArrayList<>();
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(this.path)
                .addHeader("Authorization", RestCallerConstant.AUTH_TOKEN)
                .build();

        try {
            response = client.newCall(request).execute();

            String bodyString = response.body().string();

            return new Gson().fromJson(bodyString, this.typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null; // TODO
        }
    }

    @Override
    public T create(T item) {
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(this.path)
                .addHeader("Authorization", RestCallerConstant.AUTH_TOKEN)
                .post(RequestBody.create(JSON, new Gson().toJson(item)))
                .build();

        try {
            response = client.newCall(request).execute();

            String bodyString = response.body().string();

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // TODO
        }
    }

    @Override
    public T update(T item) {
        return null;
    }

    @Override
    public String delete(int id) {
        return null;
    }

    @Override
    public T get(int id) {
        return null;
    }

}
