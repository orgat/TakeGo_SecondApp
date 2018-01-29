package com.revenant.takego_secondapp.controller;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.revenant.takego_secondapp.R;
import com.revenant.takego_secondapp.controller.services.AvailableCarsService;
import com.revenant.takego_secondapp.model.backend.Constants;
import com.revenant.takego_secondapp.model.backend.DBManagerFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;
    private BroadcastReceiver myReciever;
    private AvailableCarsFragment availableCarsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, AvailableCarsService.class);
        startService(i);

        myReciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (availableCarsFragment != null) {
                    availableCarsFragment.updateAvailableCarsList();
                    Toast.makeText(getBaseContext(), "Available-Cars list updated!", Toast.LENGTH_LONG).show();
                }
            }
        };

            setUpUI();


        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            if (nToggle.onOptionsItemSelected(item))
                return true;

            return super.onOptionsItemSelected(item);
        }

    private void setUpUI() {
        myDrawerLayout = findViewById(R.id.myDrawerLayoutId);
        navigationView = findViewById(R.id.navigationViewId);
        nToggle = new ActionBarDrawerToggle(this, myDrawerLayout, R.string.open, R.string.close);
        myDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new AboutFragment());
            fragmentTransaction.commit();


        } else if (id == R.id.nav_branches) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            Fragment fragment = new BranchesFragment();
            fragment.setArguments(getIntent().getExtras());
            fragmentTransaction.replace(R.id.main_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_availableCars) {
            availableCarsFragment = new AvailableCarsFragment();
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, availableCarsFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_rentedCars) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            Fragment fragment = new UserReservationsFragment();
            fragment.setArguments(getIntent().getExtras());
            fragmentTransaction.replace(R.id.main_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_exit) {


            System.exit(0);
        }


        myDrawerLayout.closeDrawers();
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(myReciever,
                new IntentFilter(Constants.AVAILABLE_CARS_CHANGED));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReciever);
    }
}
