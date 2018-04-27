package com.smoo182.wguplanner.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.smoo182.wguplanner.R;

public abstract class BaseSecondaryActivity extends AppCompatActivity {

    @Override
    protected void onStart(){
        super.onStart();
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
