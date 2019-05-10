package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
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

    public static int navItemIndex = 0;

    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        handler.post(runnableCode);

        setContentView(R.layout.activity_list);

        mNavigationView = findViewById(R.id.navViewList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.listActivity);
        mToggle = findViewById(R.id.toggleButton);

        mToggle.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(mNavigationView);
        });

        setUpNavigationView();

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


    public void removeItem(int position) {
        mMedicList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MedicAdapter(mMedicList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MedicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ListActivity.this, MedProfileActivity.class);
                intent.putExtra("position", mMedicList.get(position).getId());
                startActivity(intent);
            }
            public void onFavoriteClick(int position) {
                mMedicList.get(position).setFavorite(true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.map) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.List) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        if (id == R.id.Profile) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectNavMenu() {
        mNavigationView.getMenu().getItem(1).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.List:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.Profile:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(ListActivity.this, SplashActivity.class));
                        break;
                    case R.id.favourites:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(ListActivity.this, SplashActivity.class));
                        break;
                    case R.id.premium:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(ListActivity.this, SplashActivity.class));
                        break;
                    case R.id.map:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(ListActivity.this, SplashActivity.class));
                        return true;
                    case R.id.settings:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(ListActivity.this, SplashActivity.class));
                        return true;
                    case R.id.signIn:
                        startActivity(new Intent(ListActivity.this, SingInActivity.class));
                        mDrawerLayout.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
