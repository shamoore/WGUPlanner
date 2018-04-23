package com.smoo182.wguplanner.view.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.widget.TextView;

import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.FakeDataSource;
import com.smoo182.wguplanner.data.datatypes.Quote;


public class TermListActivity extends BaseActivity {
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

    }


}
