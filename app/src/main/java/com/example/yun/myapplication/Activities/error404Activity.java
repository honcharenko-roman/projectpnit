package com.example.yun.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yun.myapplication.LocalDb.LoggedUser;
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
    TextView mNameText;
    TextView mEmailText;
    View mHeaderView;
    private NavigationView mNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_testing);

        mNavigationView = findViewById(R.id.navViewList2);
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
            mNameText.setText("");
            mEmailText.setText("Please sign in");
        }

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