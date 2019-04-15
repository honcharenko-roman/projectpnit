package com.example.yun.myapplication.Retrofit;

import com.example.yun.myapplication.Entities.Medic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("/medic")
    public Call<Medic> getPostWithID(@Query("id") int id);

    @GET("/profiles/medics")
    public Call<List<Medic>> getAllPosts();
}
