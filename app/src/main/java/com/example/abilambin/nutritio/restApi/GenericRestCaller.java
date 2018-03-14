package com.example.abilambin.nutritio.restApi;

import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.utils.BackgroundRestCaller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by bellamy on 15/02/18.
 */

public class GenericRestCaller<T> implements RestCallerInterface<T>  {
    protected TypeToken<List<T>> listTypeToken;
    protected TypeToken<T> typeToken;
    protected String path;

    public GenericRestCaller(String itemName, TypeToken<List<T>> listTypeToken, TypeToken<T> typeToken){
        this.listTypeToken = listTypeToken;
        this.typeToken = typeToken;
        this.path = RestCallerConstant.SERVER_ADDR + "/api/" + itemName;
    }

    @Override
    public List<T> getAll() throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException {
        BackgroundRestCaller bgCaller = new BackgroundRestCaller();
        Request request = new Request.Builder()
                .url(this.path)
                .addHeader("Authorization", AuthenticateUser.getInstance().getAuthToken())
                .build();

        bgCaller.execute(request);

        String res = bgCaller.get();

        if(bgCaller.getResponseCode() >= 300){
            throw new WebServiceCallException(res);
        }

        if(res != null){
            return new Gson().fromJson(bgCaller.get(), this.listTypeToken.getType());
        }else{
            throw new WebServiceCallException();
        }
    }

    @Override
    public List<T> getAllOf(Integer id) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException {
        BackgroundRestCaller bgCaller = new BackgroundRestCaller();
        Request request = new Request.Builder()
                .url(this.path+"/of/"+id)
                .addHeader("Authorization", AuthenticateUser.getInstance().getAuthToken())
                .build();

        bgCaller.execute(request);

        String res = bgCaller.get();

        if(bgCaller.getResponseCode() >= 300){
            throw new WebServiceCallException(res);
        }

        if(res != null){
            return new Gson().fromJson(bgCaller.get(), this.listTypeToken.getType());
        }else{
            throw new WebServiceCallException();
        }
    }



    @Override
    public List<T> getAllOfBetween(Integer id, Date start, Date end) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


        BackgroundRestCaller bgCaller = new BackgroundRestCaller();
        Request request = new Request.Builder()
                .url(this.path+"/of/"+id+"/between/"+dateFormat.format(start)+"/"+dateFormat.format(end))
                .addHeader("Authorization", AuthenticateUser.getInstance().getAuthToken())
                .build();

        bgCaller.execute(request);

        String res = bgCaller.get();

        if(bgCaller.getResponseCode() >= 300){
            throw new WebServiceCallException(res);
        }

        if(res != null){
            return new Gson().fromJson(bgCaller.get(), this.listTypeToken.getType());
        }else{
            throw new WebServiceCallException();
        }
    }

    @Override
    public T create(T item) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException {
        BackgroundRestCaller bgCaller = new BackgroundRestCaller();
        Request request = new Request.Builder()
                .url(this.path)
                .addHeader("Authorization", AuthenticateUser.getInstance().getAuthToken())
                .post(RequestBody.create(RestCallerConstant.JSON_MEDIATYPE, new Gson().toJson(item)))
                .build();

        bgCaller.execute(request);

        String res = bgCaller.get();

        if(res != null){
            return new Gson().fromJson(bgCaller.get(), this.typeToken.getType());
        }else{
            throw new WebServiceCallException();
        }

    }

    @Override
    public T update(T item) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException {
        BackgroundRestCaller bgCaller = new BackgroundRestCaller();
        Request request = new Request.Builder()
                .url(this.path)
                .addHeader("Authorization", AuthenticateUser.getInstance().getAuthToken())
                .put(RequestBody.create(RestCallerConstant.JSON_MEDIATYPE, new Gson().toJson(item)))
                .build();

        bgCaller.execute(request);

        String res = bgCaller.get();

        if(res != null){
            return new Gson().fromJson(bgCaller.get(), this.typeToken.getType());
        }else{
            throw new WebServiceCallException();
        }
    }

    @Override
    public int delete(int id) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException {
        BackgroundRestCaller bgCaller = new BackgroundRestCaller();
        Request request = new Request.Builder()
                .url(this.path + "/" + id)
                .addHeader("Authorization", AuthenticateUser.getInstance().getAuthToken())
                .delete()
                .build();

        bgCaller.execute(request);

        String res = bgCaller.get();

        if(res != null){
            if (res.equals("")) res = "0";
            return Integer.parseInt(res);
        }else{
            throw new WebServiceCallException();
        }
    }

    @Override
    public T get(int id) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException {
        BackgroundRestCaller bgCaller = new BackgroundRestCaller();
        Request request = new Request.Builder()
                .url(this.path + "/" + id)
                .addHeader("Authorization", AuthenticateUser.getInstance().getAuthToken())
                .build();

        bgCaller.execute(request);

        String res = bgCaller.get();

        if(res != null){
            return new Gson().fromJson(bgCaller.get(), this.typeToken.getType());
        }else{
            throw new WebServiceCallException();
        }
    }

}
