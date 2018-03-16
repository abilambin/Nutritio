package com.example.abilambin.nutritio.exception;

public class WebServiceCallException extends Exception{

    public WebServiceCallException(){
        super("Error during webservice call");
    }

    public WebServiceCallException(String message) {
        super(message);
    }
}
