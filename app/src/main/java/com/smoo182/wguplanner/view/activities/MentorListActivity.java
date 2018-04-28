package com.smoo182.wguplanner.view.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.smoo182.wguplanner.R;

public class MentorListActivity extends BasePrimaryActivity {
    @Override
    int getContentViewId() {
        return R.layout.activity_mentor_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_mentors;
    }
    @Override
    void populateScreen() {
        FloatingActionButton addFab = findViewById(R.id.fab_add_mentor);
        addFab.setOnClickListener(addFabListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Mentor List");
        setSupportActionBar(toolbar);

    }

    private View.OnClickListener addFabListener = v -> startActivity(new Intent(MentorListActivity.this, MentorDetailActivity.class));

}
