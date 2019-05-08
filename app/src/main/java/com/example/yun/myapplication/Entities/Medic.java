package com.example.yun.myapplication.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Medic {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("telephone")
    @Expose
    private String telephone;

    @SerializedName("e_mail")
    @Expose
    private String email;

    @SerializedName("experience")
    @Expose
    private Integer experience;

    @SerializedName("jhi_password")
    @Expose
    private String password;

    @SerializedName("adress")
    @Expose
    private String adress;

    @SerializedName("info")
    @Expose
    private String info;

    @SerializedName("verification")
    @Expose
    private Boolean verification;

    @SerializedName("category")
    @Expose
    private String category;

    @Ignore
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = new ArrayList<>();

    public Medic(long id, String name, String surname, String telephone, String email, Integer experience, String password, String adress, String info, Boolean verification, String category, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
        this.experience = experience;
        this.password = password;
        this.adress = adress;
        this.info = info;
        this.verification = verification;
        this.category = category;
        this.comments = comments;
    }

    public Medic(String name, String surname, String telephone, String email, Integer experience, String password, String adress, String info, Boolean verification, String category, List<Comment> comments) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
        this.experience = experience;
        this.password = password;
        this.adress = adress;
        this.info = info;
        this.verification = verification;
        this.category = category;
        this.comments = comments;
    }

    public Medic() {
    }

    public Medic(String name) {
        this.name = name;
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

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
