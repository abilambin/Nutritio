package com.example.abilambin.nutritio.exception;

/**
 * Created by bellamy on 23/02/18.
 */

public class CannotAuthenticateUserException extends Exception{

    public CannotAuthenticateUserException(){
        super("Unable to authenticate user");
    }

    public CannotAuthenticateUserException(Throwable t){
        super("Unable to authenticate user", t);
    }


    public CannotAuthenticateUserException(String message){
        super(message);
    }

    public CannotAuthenticateUserException(String message, Throwable t){
        super(message, t);
    }
}
