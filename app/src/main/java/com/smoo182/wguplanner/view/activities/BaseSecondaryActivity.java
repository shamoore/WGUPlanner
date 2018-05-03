package com.smoo182.wguplanner.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Course;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSecondaryActivity extends AppCompatActivity {


    @Override
    protected void onStart(){
        super.onStart();


        populateScreen();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        populateScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.details_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    void populateScreen() {

    }
}
