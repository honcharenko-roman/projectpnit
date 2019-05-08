package com.example.yun.myapplication.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.yun.myapplication.R;

public class DoctorClientActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorclient);

        Typeface font = Typeface.createFromAsset(getAssets(), "Montserrat-Italic.ttf");
        ((TextView)findViewById(R.id.docorclientDoctorText)).setTypeface(font);
        ((TextView)findViewById(R.id.docorclientPatientText)).setTypeface(font);

        findViewById(R.id.docorclientTopFrameLayout).setOnClickListener(v->{

        });
    }
}
