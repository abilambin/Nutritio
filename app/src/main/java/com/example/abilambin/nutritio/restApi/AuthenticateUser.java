package com.example.abilambin.nutritio.restApi;

import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.utils.BackgroundRestCaller;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import okhttp3.Request;
import okhttp3.RequestBody;


public class AuthenticateUser {
    private static AuthenticateUser authenticateUser;
    private String login;
    private String password;
    private String authToken;

    private AuthenticateUser(){

    }

    public static AuthenticateUser getInstance(){
        if(authenticateUser != null){
            return authenticateUser;
        }

        authenticateUser = new AuthenticateUser();

        return authenticateUser;
    }

    /**
     * Authentifie un utilisateur
     * @return True si l'utilisateur est trouvé, false sinon
     * @throws CannotAuthenticateUserException Si une erreur surviens lors de l'authentification
     */
    private boolean makeAuth() throws CannotAuthenticateUserException {
        if(this.login == null || this.password == null){
            throw new CannotAuthenticateUserException("Login or/and password null. You must have forgotten to call the setAuthenticateInfo() method");
        }

        JsonObject info = new JsonObject();
        info.addProperty("username", this.login);
        info.addProperty("password", this.password);
        info.addProperty("rememberMe", true);

        Request request = new Request.Builder()
                .url(RestCallerConstant.SERVER_ADDR + "/api/authenticate")
                .post(RequestBody.create(RestCallerConstant.JSON_MEDIATYPE, new Gson().toJson(info)))
                .build();

        BackgroundRestCaller caller = new BackgroundRestCaller();
        caller.execute(request);

        try {
            String res = caller.get();

            JsonObject jsonRes = new JsonParser().parse(res).getAsJsonObject();
            JsonElement token = jsonRes.get("id_token");

            if(token == null){
                JsonElement status = jsonRes.get("status");

                // Utilisateur inconnu
                if(status != null && status.getAsInt() == 401){
                    return false;
                }

                throw new CannotAuthenticateUserException("Unable to find response id token");
            }

            this.authToken = "Bearer " + token.getAsString();
            return true;
        } catch (Exception e) {
            throw new CannotAuthenticateUserException(e);
        }
    }

    public String getAuthToken() throws CannotAuthenticateUserException {
        if(authToken != null){
            return authToken;
        }

        this.makeAuth();

        return this.authToken;
    }

    public void setAuthenticateInfo(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String testAuthenticateInfo() throws CannotAuthenticateUserException {
        if(this.makeAuth())
            return this.authToken;

        return null;
    }

    public void setAuthenticateToken(String authToken) {
        this.authToken = authToken;
    }
}
