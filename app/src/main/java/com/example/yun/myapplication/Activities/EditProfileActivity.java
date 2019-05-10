package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.Retrofit.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private Medic medic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        EditText nameField  = findViewById(R.id.editProfileNameField);
        EditText surnameField  = findViewById(R.id.editProfileSurnameField);
        EditText addressField  = findViewById(R.id.editProfileAddressField);
        EditText infoField  = findViewById(R.id.editProfileInformationField);
        EditText experienceField  = findViewById(R.id.editProfileExperienceField);
        EditText telephoneField  = findViewById(R.id.editProfilePhoneNumberField);

        findViewById(R.id.sendButton).setOnClickListener(v->{

            medic = new Medic(
                    nameField.getText().toString(),
                    surnameField.getText().toString(),
                    telephoneField.getText().toString(),
                    null,
                    Integer.parseInt(experienceField.getText().toString()),
                    null,
                    addressField.getText().toString(),
                    infoField.getText().toString(),
                    true,
                    null,
                    false
            );
            Call<Medic> call = NetworkService.getInstance().getJSONApi().postMedic(medic);
            call.enqueue(new Callback<Medic>() {
                @Override
                public void onResponse(Call<Medic> call, Response<Medic> response) {

                }

                @Override
                public void onFailure(Call<Medic> call, Throwable t) {

                }
            });
            startActivity(new Intent(this, ListActivity.class));
        });
    }
}
