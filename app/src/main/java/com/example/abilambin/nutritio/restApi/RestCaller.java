package com.example.abilambin.nutritio.restApi;

import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bellamy on 15/02/18.
 */

public class RestCaller <T> implements RestCallerInterface<T>{
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

            System.out.println(bodyString);

            return new Gson().fromJson(bodyString, this.typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null; // TODO
        }

    }

    @Override
    public T create(T item) {
        return null;
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

    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }
}
