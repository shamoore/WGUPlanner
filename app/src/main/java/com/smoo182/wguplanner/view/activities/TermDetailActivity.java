package com.smoo182.wguplanner.view.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

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


