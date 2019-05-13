package com.YELL.pocketMedic.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.YELL.pocketMedic.R;

public class DoctorClientActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorclient);

        Typeface font = Typeface.createFromAsset(getAssets(), "Montserrat-Italic.ttf");

        findViewById(R.id.whitePanelDoctor).setOnClickListener(v -> {
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.whitePanelClient).setOnClickListener(v -> {
            Intent intent = new Intent(this, EditClientProfileActivity.class);
            startActivity(intent);
        });
    }
}
