package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.example.yun.myapplication.R;

public class error404Activity extends AppCompatActivity {

    /**
     * Splash Screen
     *
     * @param savedInstanceState
     */



    private DrawerLayout mDrawerLayout;
    private Button mToggle;
    public static int navItemIndex = 0;

    private NavigationView mNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_testing);
        mNavigationView = findViewById(R.id.navViewList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.listActivity);
        mToggle = findViewById(R.id.toggleButton);

        mToggle.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(mNavigationView);
        });

        setUpNavigationView();

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
                        startActivity(new Intent(error404Activity.this, ListActivity.class));
                        break;
                    case R.id.Profile:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(error404Activity.this, error404Activity.class));
                        break;
                    case R.id.favourites:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(error404Activity.this, error404Activity.class));
                        break;
                    case R.id.premium:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(error404Activity.this, error404Activity.class));
                        break;
                    case R.id.map:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(error404Activity.this, error404Activity.class));
                        return true;
                    case R.id.settings:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(error404Activity.this, error404Activity.class));
                        return true;
                    case R.id.signIn:
                        startActivity(new Intent(error404Activity.this, SingInActivity.class));
                        mDrawerLayout.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }
                return true;
            }
        });
    }


}