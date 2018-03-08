package com.example.abilambin.nutritio.exception;

import com.google.gson.JsonElement;

/**
 * Created by bellamy on 08/03/18.
 */

public class CannotCreateUserException extends Exception {
    public CannotCreateUserException(String message) {
        super(message);
    }

    public CannotCreateUserException(){
        super("Impossible de créer l'utilisateur");
    }
}
