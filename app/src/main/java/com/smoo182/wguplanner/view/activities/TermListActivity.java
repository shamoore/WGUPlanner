package com.smoo182.wguplanner.view.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.FakeDataSource;
import com.smoo182.wguplanner.data.datatypes.Quote;


public class TermListActivity extends BaseActivity{


    @Override
    int getContentViewId() {
        return R.layout.activity_term_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_terms;
    }

    @Override
    void populateScreen() {
        FloatingActionButton addFab = findViewById(R.id.fab_add_term);
        addFab.setOnClickListener(addFabListener);

   }

   private View.OnClickListener addFabListener = v -> startActivity(new Intent(TermListActivity.this, TermDetailActivity.class));

}
