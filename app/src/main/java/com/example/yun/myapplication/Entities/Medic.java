//package com.example.yun.myapplication.Entities;
//
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.Ignore;
//import android.arch.persistence.room.PrimaryKey;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Medic {
//
////    @PrimaryKey
////    @SerializedName("id")
////    @Expose
//     long id;
//
////    @SerializedName("name")
////    @Expose
//     String name;
//
////    @SerializedName("surname")
////    @Expose
//     String surname;
//
////    @SerializedName("telephone")
////    @Expose
//     String telephone;
//
////    @SerializedName("e_mail")
////    @Expose
//     String email;
//
////    @SerializedName("experience")
////    @Expose
//     Integer experience;
//
////    @SerializedName("jhi_password")
////    @Expose
//     String password;
//
////    @SerializedName("adress")
////    @Expose
//     String adress;
//
////    @SerializedName("info")
////    @Expose
//     String info;
//
////    @SerializedName("verification")
////    @Expose
//     Boolean verification;
//
////    @SerializedName("category")
////    @Expose
//     String category;
//
////    @Ignore
////    @SerializedName("comments")
////    @Expose
//     List<Comment> comments = new ArrayList<>();
//
//    public Medic(String name) {
//        this.id = 0;
//        this.name = name;
//        this.surname = null;
//        this.telephone = null;
//        this.email = null;
//        this.experience = null;
//        this.password = null;
//        this.adress = null;
//        this.info = null;
//        this.verification = null;
//        this.category = null;
//        this.comments = null;
//    }
//
////    public Medic(String name) {
////        this.name = name;
////    }
//
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public Integer getExperience() {
//        return experience;
//    }
//
//    public void setExperience(Integer experience) {
//        this.experience = experience;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getAdress() {
//        return adress;
//    }
//
//    public void setAdress(String adress) {
//        this.adress = adress;
//    }
//
//    public String getInfo() {
//        return info;
//    }
//
//    public void setInfo(String info) {
//        this.info = info;
//    }
//
//    public Boolean getVerification() {
//        return verification;
//    }
//
//    public void setVerification(Boolean verification) {
//        this.verification = verification;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//}
//
//
//


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

    Long id;
    String name;
    String surname;
    String telephone;
    String email;
    Integer experience;
    String password;
    String adress;
    String info;
    Boolean verification;
    String category;
    List<Comment> comments = new ArrayList<>();


    public Medic(String name, String surname, String telephone, String email, Integer experience, String password, String adress, String info, Boolean verification, String category) {
        this.id = null;
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
        comments = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}