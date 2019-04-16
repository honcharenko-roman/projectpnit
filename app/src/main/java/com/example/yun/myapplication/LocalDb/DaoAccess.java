package com.example.yun.myapplication.LocalDb;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.yun.myapplication.Entities.Favourites;
import com.example.yun.myapplication.Entities.Medic;

import java.util.List;

@Dao
public interface DaoAccess {

    @Query("SELECT * FROM medic")
    List<Medic> getAll();

    @Insert
    void insertFavourite(Medic medic);

    @Insert
    void insertListOfFavourites(List<Medic> favouritesMedicsList);

    @Update
    void updateFavourite(Medic medic);

    @Delete
    void deleteFavourite(Medic medic);
}

