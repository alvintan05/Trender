package com.spnj.trender;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";

    private TextView mTextMessage;
    private Toolbar mToolbar;
    private DrawerLayout dl;
    private NavigationView nv;
    private ActionBarDrawerToggle ab;

    private ArrayList<String> mName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //mendefinisikan untuk teks di bottom navigation
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //

//        //mendefinisikan toolbar
        mToolbar = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(mToolbar);

        //Mendefinisikan Navigation Drawer

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        ab = new ActionBarDrawerToggle(this, dl, mToolbar, R.string.open, R.string.close);
        dl.addDrawerListener(ab);
        ab.syncState();
        nv = (NavigationView) findViewById(R.id.navigationview);
        nv.setNavigationItemSelectedListener(this);

        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.fragment_container, fragment, "Home Fragment");
        fragmentTransaction1.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.fragment_container, fragment, "Home Fragment");
                    fragmentTransaction1.commit();
                    return true;
                case R.id.navigation_add:
                    mTextMessage.setText(R.string.title_add);
                    return true;
                case R.id.navigation_account:
                    ProfileFragment fragment2 = new ProfileFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.fragment_container, fragment2, "Profile Fragment");
                    fragmentTransaction2.commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ootd:
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buy:
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dining:
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.report:
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.signout:
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}



