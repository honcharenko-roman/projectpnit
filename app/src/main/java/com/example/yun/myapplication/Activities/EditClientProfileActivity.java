package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yun.myapplication.Entities.Account;
import com.example.yun.myapplication.LocalDb.LoggedUser;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.Retrofit.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditClientProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_client);

        EditText nameField = findViewById(R.id.editClientProfileNameField);
        EditText surnameField = findViewById(R.id.editClientProfileSurnameField);
        EditText addressField = findViewById(R.id.editClientProfileAddressField);
        EditText infoField = findViewById(R.id.editClientProfileInformationField);
        EditText telephoneField = findViewById(R.id.editClientProfilePhoneNumberField);

        findViewById(R.id.sendButton).setOnClickListener(v -> {

            Account account = new Account(
                    nameField.getText().toString(),
                    surnameField.getText().toString(),
                    telephoneField.getText().toString(),
                    null,
                    null,
                    infoField.getText().toString(),
                    addressField.getText().toString()
            );

            LoggedUser.getInstance().setName(account.getName());
            LoggedUser.getInstance().setEmail(account.getName().toLowerCase().replace(" ", "") + account.getSurname() + "@gmail.com");
            LoggedUser.getInstance().setLoggedIn(true);


            Call<Account> call = NetworkService
                    .getInstance().getJSONApi()
                    .postAccount(account);
            call.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {

                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {

                }
            });
            startActivity(new Intent(this, ListActivity.class));
        });
    }
}
