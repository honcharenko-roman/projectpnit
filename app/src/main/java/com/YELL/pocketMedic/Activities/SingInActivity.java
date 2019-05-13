package com.YELL.pocketMedic.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.YELL.pocketMedic.R;

public class SingInActivity extends AppCompatActivity {


    /**
     * Sign In Activity
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Typeface font = Typeface.createFromAsset(getAssets(), "Montserrat-Black.ttf");

        font = Typeface.createFromAsset(getAssets(), "Montserrat-Italic.ttf");
        ((TextView)findViewById(R.id.forgotPasswordText)).setTypeface(font);
        ((TextView)findViewById(R.id.createAccountText)).setTypeface(font);
        ((EditText)findViewById(R.id.passEditText)).setTypeface(font);
        ((EditText)findViewById(R.id.emailEditText)).setTypeface(font);

        findViewById(R.id.createAccountText).setOnClickListener(v-> {
            Intent intent = new Intent(this, DoctorClientActivity.class);
            startActivity(intent);
        });
    }
}
