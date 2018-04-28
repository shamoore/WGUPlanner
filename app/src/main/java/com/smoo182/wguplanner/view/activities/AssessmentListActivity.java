package com.smoo182.wguplanner.view.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.smoo182.wguplanner.R;

public class AssessmentListActivity extends BasePrimaryActivity {
    @Override
    int getContentViewId() {
        return R.layout.activity_assessment_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_assessments;
    }
    @Override
    void populateScreen() {
        FloatingActionButton addFab = findViewById(R.id.fab_add_assessment);
        addFab.setOnClickListener(addFabListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Assessment List");
        setSupportActionBar(toolbar);

    }
    private View.OnClickListener addFabListener = v -> startActivity(new Intent(AssessmentListActivity.this, AssessmentDetailActivity.class));

}
