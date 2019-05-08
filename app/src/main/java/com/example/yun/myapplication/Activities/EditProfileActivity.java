package com.example.yun.myapplication.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.Retrofit.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private Medic medic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        EditText nameField  = (EditText)findViewById(R.id.nameField);

        medic = new Medic(
                nameField.getText().toString()
        );

        findViewById(R.id.EditProfileText).setOnClickListener(v->{
            NetworkService.getInstance()
                    .getJSONApi()
                    .postMedic(medic)
                    .enqueue(new Callback<Medic>() {
                        @Override
                        public void onResponse(@NonNull Call<Medic> call, @NonNull Response<Medic> response) {
                        }

                        @Override
                        public void onFailure(@NonNull Call<Medic> call, @NonNull Throwable t) {
                            t.printStackTrace();
                        }
                    });
        });
    }
}
