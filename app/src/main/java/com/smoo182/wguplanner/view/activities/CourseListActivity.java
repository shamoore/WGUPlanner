package com.smoo182.wguplanner.view.activities;

import com.smoo182.wguplanner.R;

public class CourseListActivity extends BasePrimaryActivity {
    @Override
    int getContentViewId() {
        return R.layout.activity_course_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_courses;
    }

    @Override
    void populateScreen() {

    }
}
