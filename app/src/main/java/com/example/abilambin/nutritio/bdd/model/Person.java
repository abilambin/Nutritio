package com.example.abilambin.nutritio.bdd.model;

import com.example.abilambin.nutritio.bdd.model.ingredientList.BlackList;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Grocerie;
import com.example.abilambin.nutritio.bdd.model.ingredientList.Stock;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable{

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

    public BlackList getBlacklist() { return blacklist; }

    public void setBlacklist(BlackList blacklist) { this.blacklist = blacklist; }

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
