package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.LocalDb.LoggedUser;
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

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setAdapter(new ArrayAdapter<YELL.main.domain.Categories>(this, android.R.layout.simple_spinner_item, YELL.main.domain.Categories.values()));

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
                    mySpinner.getSelectedItem().toString(),
                    false
            );

            //logging in
            LoggedUser.getInstance().setName(medic.getName());
            LoggedUser.getInstance().setEmail(medic.getName().toLowerCase().replace(" ", "") + medic.getSurname() + "@gmail.com");
            LoggedUser.getInstance().setLoggedIn(true);

            Call<Medic> call = NetworkService.getInstance().getJSONApi().postMedic(medic);
            call.enqueue(new Callback<Medic>() {
                @Override
                public void onResponse(Call<Medic> call, Response<Medic> response) {

                }

                @Override
                public void onFailure(Call<Medic> call, Throwable t) {

                }
            });


            NavigationView mNavigationView = findViewById(R.id.navViewList);
            DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.listActivity);

//            findViewById(R.id.navHeaderName).

//            mNavigationView.getMenu().getItem(0).setEnabled(false);

            startActivity(new Intent(this, ListActivity.class));
        });
    }
}
