package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.Retrofit.NetworkService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, ListActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            finish();
        });
    }
}

