package com.smoo182.wguplanner.view.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.smoo182.wguplanner.R;

public class MentorDetailActivity extends BaseSecondaryActivity {

    void populateScreen() {
        setContentView(R.layout.activity_mentor_detail);
        Toolbar toolbar = findViewById(R.id.details_toolbar);
        toolbar.setTitle("Mentor Details");
        setSupportActionBar(toolbar);

    }


}
