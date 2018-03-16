package com.example.abilambin.nutritio.restApi;

import com.example.abilambin.nutritio.exception.CannotAuthenticateUserException;
import com.example.abilambin.nutritio.exception.WebServiceCallException;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface RestCallerInterface<T> {

    /**
     * Récupere tous les objet du type T
     * @return la liste d'objet
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws WebServiceCallException
     * @throws CannotAuthenticateUserException
     */
    List<T> getAll() throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;

    /**
     * Récupère l'ensemble des objet de type T appartenant a l'utilisateur :id
     * @param id Id de l'utilisateur en question
     * @return La liste des objet
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws WebServiceCallException
     * @throws CannotAuthenticateUserException
     */
    List<T> getAllOf(Integer id) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;

    /**
     * Créer un item de type T
     * @param item L'item à créer
     * @return L'item créé
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws WebServiceCallException
     * @throws CannotAuthenticateUserException
     */
    T create(T item) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;

    /**
     * Modifie un item
     * @param item L'item à modifier
     * @return L'item modifié
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws WebServiceCallException
     * @throws CannotAuthenticateUserException
     */
    T update(T item) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;

    /**
     * Supprime l'item :id
     * @param id L'id de l'item à supprimer
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws WebServiceCallException
     * @throws CannotAuthenticateUserException
     */
    void delete(int id) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;

    /**
     * Récupère l'item :id
     * @param id l'id de l'item à récupérer
     * @return L'item en question
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws WebServiceCallException
     * @throws CannotAuthenticateUserException
     */
    T get(int id) throws ExecutionException, InterruptedException, WebServiceCallException, CannotAuthenticateUserException;

}
