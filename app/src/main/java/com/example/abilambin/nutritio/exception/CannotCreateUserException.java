package com.example.abilambin.nutritio.exception;


public class CannotCreateUserException extends Exception {
    public CannotCreateUserException(String message) {
        super(message);
    }

    public CannotCreateUserException(){
        super("Impossible de créer l'utilisateur");
    }
}
