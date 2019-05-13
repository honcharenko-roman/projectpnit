package com.YELL.pocketMedic.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.YELL.pocketMedic.R;

public class SplashActivity extends AppCompatActivity {

    /**
     * Splash Screen
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();
    }
}