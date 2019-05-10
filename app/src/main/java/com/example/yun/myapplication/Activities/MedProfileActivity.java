package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.Retrofit.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedProfileActivity extends AppCompatActivity {

    ImageView avatar;
    TextView name;
    TextView surname;
    TextView category;
    TextView about;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_med);

        name = findViewById(R.id.medProfileName);
        category = findViewById(R.id.medProfileCategory);
        about = findViewById(R.id.medProfileAbout);


        Intent mIntent = getIntent();
        long medicId = mIntent.getLongExtra("position", 0);

        NetworkService.getInstance()
                .getJSONApi()
                .getMedicWithID(medicId)
                .enqueue(new Callback<Medic>() {
                    @Override
                    public void onResponse(@NonNull Call<Medic> call, @NonNull Response<Medic> response) {
                        if (response.body() != null) {
                            Medic medic = response.body();
                            name.setText(medic.getName());
                            name.append(" " + medic.getSurname());
                            about.setText(medic.getInfo());
                            category.setText(medic.getCategory());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Medic> call, @NonNull Throwable t) {
                        name.append("Error occurred while getting request!");
                        category.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }
}
