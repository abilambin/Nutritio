package com.example.abilambin.nutritio.restApi;

import com.example.abilambin.nutritio.bdd.model.Ingredient;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.utils.BackgroundRestCaller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by bellamy on 15/02/18.
 */

public class GenericRestCaller<T> implements RestCallerInterface<T>  {
    protected final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    protected TypeToken<List<Ingredient>> listTypeToken;
    protected TypeToken<Ingredient> typeToken;
    protected String path;

    public GenericRestCaller(String itemName, TypeToken<List<Ingredient>> listTypeToken, TypeToken<Ingredient> typeToken){
        this.listTypeToken = listTypeToken;
        this.typeToken = typeToken;
        this.path = RestCallerConstant.SERVER_ADDR + "/api/" + itemName;
    }

    @Override
    public List<T> getAll() throws ExecutionException, InterruptedException, WebServiceCallException {
        BackgroundRestCaller bgCaller = new BackgroundRestCaller();
        Request request = new Request.Builder()
                .url(this.path)
                .addHeader("Authorization", RestCallerConstant.AUTH_TOKEN)
                .build();

        bgCaller.execute(request);

        String res = bgCaller.get();

        if(res != null){
            return new Gson().fromJson(bgCaller.get(), this.listTypeToken.getType());
        }else{
            throw new WebServiceCallException();
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
