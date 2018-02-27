package com.example.akash.menu;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by akash on 27/2/18.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsFragment settingsFragment = new SettingsFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_settings, settingsFragment);
        ft.commit();

        ActionBar actionBar = this.getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}


