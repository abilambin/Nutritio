package com.example.abilambin.nutritio.restApi;

import com.example.abilambin.nutritio.exception.WebServiceCallException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by bellamy on 15/02/18.
 */

public interface RestCallerInterface<T> {

    public List<T> getAll() throws ExecutionException, InterruptedException, WebServiceCallException;
    public T create(T item);
    public T update(T item);
    public String delete(int id);
    public T get(int id);

}
