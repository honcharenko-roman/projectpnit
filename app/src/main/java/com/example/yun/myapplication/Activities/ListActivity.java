package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.yun.myapplication.R;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button button4 = findViewById(R.id.button2);
        button4.setOnClickListener(v -> {
            Intent intent = new Intent(this, MedProfileActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
        });
        Button button1 = findViewById(R.id.button3);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(this, MedProfileActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
        });
        Button button2 = findViewById(R.id.button4);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(this, MedProfileActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
        });
        Button button3 = findViewById(R.id.button5);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(this, MedProfileActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
        });

    }
}
