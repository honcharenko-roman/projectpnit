package com.example.yun.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

public class DocOrClientActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_editprofile);

        //Typeface font = Typeface.createFromAsset(getAssets(), "Montserrat-Black.ttf");
        //((TextView)findViewById(R.id.signInText)).setTypeface(font);
    }

}
