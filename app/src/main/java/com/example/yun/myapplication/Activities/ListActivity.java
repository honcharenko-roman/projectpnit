package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ToggleButton;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.RecyclerViewAdapters.MedicAdapter;
import com.example.yun.myapplication.Retrofit.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    private List<Medic> mMedicList;

    private RecyclerView mRecyclerView;
    private MedicAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DrawerLayout mDrawerLayout;
    private Button mToggle;

    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mNavigationView = findViewById(R.id.navViewList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.listActivity);
        mToggle = findViewById(R.id.toggleButton);

        mToggle.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(mNavigationView);
        });



        mMedicList = null;

        NetworkService.getInstance()
                .getJSONApi()
                .getAllPosts()
                .enqueue(new Callback<List<Medic>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Medic>> call, @NonNull Response<List<Medic>> response) {
                        mMedicList = response.body();
                        buildRecyclerView();
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Medic>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mToggleButton.isActivated()){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    /*public void insertItem(int position) {
        mMedicList.add(position, new Medic(R.drawable.ic_android, "New Item At Position" + position, "This is Line 2"));
        mAdapter.notifyItemInserted(position);
    }*/

    public void removeItem(int position) {
        mMedicList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    /*public void changeItem(int position, String text) {
        mMedicList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }*/

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MedicAdapter(mMedicList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        /*mAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(this, MedProfileActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });*/


        findViewById(R.id.listClearDataBaseButton).setOnClickListener(v->{
            NetworkService
                    .getInstance()
                    .getJSONApi()
                    .deleteMedics()
                    .enqueue(new Callback<Medic>() {
                        @Override
                        public void onResponse(Call<Medic> call, Response<Medic> response) {

                        }

                        @Override
                        public void onFailure(Call<Medic> call, Throwable t) {

                        }
                    });
        });

        mAdapter.setOnItemClickListener(new MedicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ListActivity.this, MedProfileActivity.class);
                intent.putExtra("position", mMedicList.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onFavoriteClick(int position) {
                removeItem(position);
            }
        });

        findViewById(R.id.listContinueButton).setOnClickListener(v->{
            Intent intent = new Intent(ListActivity.this, SingInActivity.class);
            startActivity(intent);
        });
    }
}