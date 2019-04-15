package com.example.yun.myapplication.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yun.myapplication.R;

import java.util.Objects;

public class SingInActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_signin);

        Typeface font = Typeface.createFromAsset(getAssets(), "Montserrat-Black.ttf");
        ((TextView)findViewById(R.id.signInText)).setTypeface(font);

        font = Typeface.createFromAsset(getAssets(), "Montserrat-Italic.ttf");
        ((TextView)findViewById(R.id.forgotPasswordText)).setTypeface(font);
        ((TextView)findViewById(R.id.createAccountText)).setTypeface(font);
        ((EditText)findViewById(R.id.passEditText)).setTypeface(font);
        ((EditText)findViewById(R.id.emailEditText)).setTypeface(font);

        //Displaying TextInputLayout Error
        /*TextInputLayout emailLayout = (TextInputLayout) findViewById(R.id
                .fEmailLayout);
        emailLayout.setErrorEnabled(true);
        emailLayout.setError("Min 2 chars required");*/

        //Displaying EditText Error
        /*EditText age = (EditText) findViewById(R.id.emailEditText);
        age.setError("Required");*/

        //Displaying both TextInputLayout and EditText Errors
        /*TextInputLayout passLayout = (TextInputLayout) findViewById(R.id
                .fPassLayout);
        passLayout.setErrorEnabled(true);
        passLayout.setError("Please enter a phone number");
        EditText phone = (EditText) findViewById(R.id.passEditText);
        phone.setError("Required");*/
    }
}
