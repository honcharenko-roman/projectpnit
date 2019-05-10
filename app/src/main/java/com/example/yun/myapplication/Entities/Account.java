package com.example.yun.myapplication.Entities;

import android.arch.persistence.room.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Account {

    private Long id;
    private String name;
    private String surname;
    private String telephone;
    private String eMail;
    private String password;
    private String info;
    private String address;
    //private Set<AccountMedic> favourites = new HashSet<>();

    public Account(String name, String surname, String telephone, String eMail, String password, String info, String address) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.eMail = eMail;
        this.password = password;
        this.info = info;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}