package com.example.yun.myapplication.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Favourites {

    @NonNull
    @PrimaryKey
    private Medic medic;

    public Favourites() {
    }

    public Favourites(@NonNull Medic medic) {
        this.medic = medic;
    }

    @NonNull
    public Medic getMedic() {
        return medic;
    }

    public void setMedic(@NonNull Medic medic) {
        this.medic = medic;
    }
}
