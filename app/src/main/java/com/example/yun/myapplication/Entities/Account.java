package com.example.yun.myapplication.Entities;

import java.util.ArrayList;
import java.util.List;


public class Account {


    private long account_id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<Medic> favourites = new ArrayList<>();

    private Medic medic;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public List<Medic> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Medic> favourites) {
        this.favourites = favourites;
    }

    public Account() {
    }

    public Account(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}