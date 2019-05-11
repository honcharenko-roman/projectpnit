package com.example.yun.myapplication.Activities;

import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.os.Build;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.LocalDb.LoggedUser;
import com.example.yun.myapplication.R;
import com.example.yun.myapplication.RecyclerViewAdapters.MedicAdapter;
import com.example.yun.myapplication.Retrofit.NetworkService;

import java.util.ArrayList;
import java.util.Arrays;
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

    //!*($E^*)$
    private List categoryValues = new ArrayList<String>();

    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        categoryValues.add("Выберите категорию");
        categoryValues.addAll(Arrays.asList(YELL.main.domain.Categories.values()));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        mNavigationView = findViewById(R.id.navViewList);
        mHeaderView = mNavigationView.getHeaderView(0);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.listActivity);
        mToggle = findViewById(R.id.toggleButton);

        mNameText = mHeaderView.findViewById(R.id.navHeaderName);
        mEmailText = mHeaderView.findViewById(R.id.navHeaderEmail);


        if (LoggedUser.getInstance().isLoggedIn() == true) {
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

        EditText cityFilter = findViewById(R.id.listCityFilter);
        EditText nameFilter = findViewById(R.id.listNameFilter);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);
        mySpinner.setAdapter(new ArrayAdapter<List<String>>(this, android.R.layout.simple_spinner_item, categoryValues) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        });

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
                }
                filter(nameFilter.getText().toString(), cityFilter.getText().toString(), mySpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cityFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(nameFilter.getText().toString(), s.toString(), mySpinner.getSelectedItem().toString());
            }
        });
        nameFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString(), cityFilter.getText().toString(), mySpinner.getSelectedItem().toString());
            }
        });

    }

    private void filter(String name, String city, String category) {
        ArrayList<Medic> filteredList = new ArrayList<>();

        if (mMedicList != null) {
            for (Medic medic : mMedicList) {

                String nameSurname = medic.getName().concat(" " + medic.getSurname());
                String surnameName = medic.getSurname().concat(" " + medic.getName());

                boolean isNameSurnameMatches = Objects.requireNonNull(nameSurname).toLowerCase().startsWith(name.toLowerCase());
                boolean isSurnameNameMatches = Objects.requireNonNull(surnameName).toLowerCase().startsWith(name.toLowerCase());
                boolean isCityMatches = Objects.requireNonNull(medic.getAdress()).toLowerCase().startsWith(city);

                if (isCityMatches) {
                    if ((isNameSurnameMatches) || (isSurnameNameMatches)) {
                        if ((Objects.requireNonNull(category).toLowerCase()
                                .compareTo(Objects.requireNonNull(medic.getCategory()).toLowerCase()) == 0)
                                || (category.compareTo("Выберите категорию") == 0)) {
                            filteredList.add(medic);
                            mAdapter.filterList(filteredList);
                        }
                    }
                }
            }
            if (filteredList.isEmpty()) {
                mAdapter.filterList(filteredList);
            }
        }
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
            if (resultCode == Activity.RESULT_OK) {
                int result = data.getIntExtra("position", 0);
                boolean result2 = data.getBooleanExtra("isFavorite", false);
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
