package com.smoo182.wguplanner.view.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.smoo182.wguplanner.R;

public class TermDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_term_detail;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_terms;
    }

    @Override
    void populateScreen() {

    }

  //  private View.OnClickListener startDateListener = v -> new DatePickerDialog().show(getFragmentManager(),"datePickerFragment");
}
