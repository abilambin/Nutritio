package com.example.abilambin.nutritio.exception;

/**
 * Created by bellamy on 16/02/18.
 */

public class WebServiceCallException extends Exception{

    public WebServiceCallException(){
        super("Error during webservice call");
    }

    public WebServiceCallException(String message) {
        super(message);
    }
}
