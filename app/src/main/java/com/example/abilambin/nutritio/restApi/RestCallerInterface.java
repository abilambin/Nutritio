package com.example.abilambin.nutritio.restApi;

import java.io.IOException;
import java.util.List;

/**
 * Created by bellamy on 15/02/18.
 */

public interface RestCallerInterface<T> {
    public List<T> getAll() throws IOException;
    public T create(T item);
    public T update(T item);
    public String delete(int id);
    public T get(int id);
}
