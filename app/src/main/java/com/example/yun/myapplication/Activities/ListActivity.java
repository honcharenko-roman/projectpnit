package com.example.yun.myapplication.Activities;

import android.app.Activity;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.LocalDb.LoggedUser;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.RecyclerViewAdapters.MedicAdapter;
import com.example.yun.myapplication.Retrofit.NetworkService;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

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
    TextView mNameText;
    TextView mEmailText;
    View mHeaderView;
    public static int navItemIndex = 0;

    private NavigationView mNavigationView;

    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        mNavigationView = findViewById(R.id.navViewList);
        mHeaderView = mNavigationView.getHeaderView(0);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.listActivity);
        mToggle = findViewById(R.id.toggleButton);

        mNameText = mHeaderView.findViewById(R.id.navHeaderName);
        mEmailText = mHeaderView.findViewById(R.id.navHeaderEmail);



        if(LoggedUser.getInstance().isLoggedIn()==true){
            mNavigationView.getMenu().getItem(6).setVisible(false);
            mNavigationView.getMenu().getItem(7).setVisible(true);
            mNameText.setText(LoggedUser.getInstance().getName());
            mEmailText.setText(LoggedUser.getInstance().getEmail());
        } else {
            mNavigationView.getMenu().getItem(6).setVisible(true);
            mNavigationView.getMenu().getItem(7).setVisible(false);
        }

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
                Bundle extras = new Bundle();
                extras.putLong("id", mMedicList.get(position).getId());
                extras.putInt("position", position);
                extras.putBoolean("isFavorite", mMedicList.get(position).isFavorite());
                intent.putExtras(extras);
                startActivityForResult(intent, 1);
            }

            public void onFavoriteClick(int position) {
                if (mMedicList.get(position).isFavorite()) {
                    mMedicList.get(position).setFavorite(false);
                } else {
                    mMedicList.get(position).setFavorite(true);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                int result = data.getIntExtra("position",0);
                boolean result2 = data.getBooleanExtra("isFavorite",false);
                mMedicList.get(result).setFavorite(result2);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

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
                        startActivity(new Intent(ListActivity.this, error404Activity.class));
                        break;
                    case R.id.favourites:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(ListActivity.this, error404Activity.class));
                        break;
                    case R.id.premium:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(ListActivity.this, error404Activity.class));
                        break;
                    case R.id.map:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(ListActivity.this, error404Activity.class));
                        return true;
                    case R.id.settings:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(ListActivity.this, error404Activity.class));
                        return true;
                    case R.id.signIn:
                        startActivity(new Intent(ListActivity.this, SingInActivity.class));
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.logOut:
                        LoggedUser.getInstance().setLoggedIn(false);
                        startActivity(new Intent(ListActivity.this, ListActivity.class));
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
