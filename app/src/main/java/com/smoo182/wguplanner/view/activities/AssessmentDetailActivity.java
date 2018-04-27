package com.smoo182.wguplanner.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.smoo182.wguplanner.R;

public class AssessmentDetailActivity extends BaseSecondaryActivity {

    @Override
    void populateScreen() {
        setContentView(R.layout.activity_assessment_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Assessment Details");
        setSupportActionBar(toolbar);
    }

}
