package com.example.abilambin.nutritio.restApi.specific;

import com.example.abilambin.nutritio.bdd.model.ingredientList.ScoredRecipe;
import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;
import com.example.abilambin.nutritio.restApi.AuthenticateUser;
import com.example.abilambin.nutritio.restApi.RestCallerConstant;
import com.example.abilambin.nutritio.utils.BackgroundRestCaller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.Request;


public class ScoredRecipeRestCaller {
    private TypeToken<List<ScoredRecipe>> listTypeToken;

    public ScoredRecipeRestCaller() {
        this.listTypeToken = new TypeToken<List<ScoredRecipe>>(){};
    }

    public List<ScoredRecipe> getScoredRecipe(long id) throws CannotAuthenticateUserException, ExecutionException, InterruptedException, WebServiceCallException {
        BackgroundRestCaller bgCaller = new BackgroundRestCaller();
        Request request = new Request.Builder()
                .url(RestCallerConstant.SERVER_ADDR + "/api/people/"+ id +"/getSuggestions")
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
}
