package com.example.yun.myapplication.Activities;

import android.app.Activity;
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

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedProfileActivity extends AppCompatActivity {

    Medic medic;

    private ImageView avatar;
    private TextView name;
    private TextView surname;
    private TextView category;
    private TextView about;
    private ImageView favoriteButton;
    private ImageView backButton;

    int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_med);

        name = findViewById(R.id.medProfileName);
        category = findViewById(R.id.medProfileCategory);
        about = findViewById(R.id.medProfileAbout);
        favoriteButton = findViewById(R.id.medProfileFavoriteButton);
        backButton = findViewById(R.id.medProfileBackButton);


        Intent mIntent = getIntent();
        Bundle extras = mIntent.getExtras();
        long medicId = Objects.requireNonNull(extras).getLong("id", 0);
        boolean isFavorite = extras.getBoolean("isFavorite");
        position = extras.getInt("position");


        NetworkService.getInstance()
                .getJSONApi()
                .getMedicWithID(medicId)
                .enqueue(new Callback<Medic>() {
                    @Override
                    public void onResponse(@NonNull Call<Medic> call, @NonNull Response<Medic> response) {
                        if (response.body() != null) {
                            medic = response.body();
                            drawIsFavoriteButton();
                            medic.setFavorite(isFavorite);
                            if (!medic.isFavorite())
                                favoriteButton.setImageResource(R.drawable.ic_favorite_blank);
                            else
                                favoriteButton.setImageResource(R.drawable.ic_favorite_filled);

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

        favoriteButton.setOnClickListener(v -> {
            if (medic != null) {
                drawIsFavoriteButton();
            }
        });
    }

    void drawIsFavoriteButton() {
        if (medic.isFavorite()) {
            favoriteButton.setImageResource(R.drawable.ic_favorite_blank);
            medic.setFavorite(false);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite_filled);
            medic.setFavorite(true);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("isFavorite", medic.isFavorite());
        intent.putExtra("position", position);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
