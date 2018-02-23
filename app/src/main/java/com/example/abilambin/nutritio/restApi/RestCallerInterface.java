package com.example.abilambin.nutritio.restApi;

import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bellamy on 15/02/18.
 */

public interface RestCallerInterface<T> {

    public List<T> getAll() throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;
    public T create(T item) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;
    public T update(T item) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;
    public int delete(int id) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;
    public T get(int id) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;

}
