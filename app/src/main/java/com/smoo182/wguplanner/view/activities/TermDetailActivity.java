package com.smoo182.wguplanner.view.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.smoo182.wguplanner.R;

public class TermDetailActivity extends BaseSecondaryActivity {

    @Override
    void populateScreen() {
        setContentView(R.layout.activity_term_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Term Details");
        setSupportActionBar(toolbar);
    }


}


