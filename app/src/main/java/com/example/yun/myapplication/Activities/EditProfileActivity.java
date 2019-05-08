package com.example.yun.myapplication.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.Entities.Medic2;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.Retrofit.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private Medic2 medic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        EditText nameField  = findViewById(R.id.nameField);

        nameField.getText().append("huy");
        medic = new Medic2(
                nameField.getText().toString()
        );


        findViewById(R.id.EditProfileText).setOnClickListener(v->{

            Call<Medic2> call = NetworkService.getInstance().getJSONApi().postMedic(medic);
            call.enqueue(new Callback<Medic2>() {
                @Override
                public void onResponse(Call<Medic2> call, Response<Medic2> response) {

                }

                @Override
                public void onFailure(Call<Medic2> call, Throwable t) {

                }
            });


//            NetworkService.getInstance()
//                    .getJSONApi()
//                    .postMedic(medic)
//                    .enqueue(new Callback<Medic2>() {
//                        @Override
//                        public void onResponse(@NonNull Call<Medic2> call, @NonNull Response<Medic2> response) {
//                        }
//
//                        @Override
//                        public void onFailure(@NonNull Call<Medic2> call, @NonNull Throwable t) {
//                            t.printStackTrace();
//                        }
//                    });
        });
    }
}
