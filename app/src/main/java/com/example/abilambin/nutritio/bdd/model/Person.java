package com.example.abilambin.nutritio.bdd.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by bellamy on 23/02/18.
 */

public class Person {

    private static long serialVersionUID = 1L;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private Date birthday;

    @DatabaseField
    private String email;

    @DatabaseField
    private String firstname;

    @DatabaseField
    private String lastname;

    @DatabaseField (canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Stock stock;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Grocerie grocerie;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private BlackList blacklist;

    public Person(int id, Date birthday, String email, String firstname, String lastname, Stock stock, Grocerie grocerie) {
        this.id = id;
        this.birthday = birthday;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.stock = stock;
        this.grocerie = grocerie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Grocerie getGrocerie() {
        return grocerie;
    }

    public void setGrocerie(Grocerie grocerie) {
        this.grocerie = grocerie;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", stock=" + stock +
                ", grocerie=" + grocerie +
                ", blacklist=" + blacklist +
                '}';
    }
}
