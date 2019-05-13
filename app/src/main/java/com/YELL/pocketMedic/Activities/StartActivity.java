package com.YELL.pocketMedic.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.YELL.pocketMedic.R;

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

