package com.example.yun.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Objects;

public class SingInActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_signIn);

        Typeface font = Typeface.createFromAsset(getAssets(), "Montserrat-Black.ttf");
        ((TextView)findViewById(R.id.signInText)).setTypeface(font);
    }
}
