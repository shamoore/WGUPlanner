package com.smoo182.wguplanner.view.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.smoo182.wguplanner.R;

import java.util.ArrayList;
import java.util.List;

public class TermDetailActivity extends BaseSecondaryActivity {

    private static final String EXTRA_TERM_TITLE = "EXTRA_TERM_TITLE";
    private static final String EXTRA_TERM_DESCRIPTION= "EXTRA_TERM_DESCRIPTION";
    private static final String EXTRA_STARTDATE= "EXTRA_STARTDATE";
    private static final String EXTRA_STOPDATE = "EXTRA_STOPDATE";
    private static final ArrayList<String> EXTRA_COURSE_LIST =   new ArrayList<>();

    private TextView termTitle;
    private TextView termDescription;
    private TextView termStartDate;
    private TextView termStopDate;
    private ListView termCourseList;



    @Override
    void populateScreen() {
        setContentView(R.layout.activity_term_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Term Details");
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String termTitleExtra = i.getStringExtra(EXTRA_TERM_TITLE);
        String termDescExtra = i.getStringExtra(EXTRA_TERM_DESCRIPTION);
        String termStartExtra = i.getStringExtra(EXTRA_STARTDATE);
        String termStopExtra = i.getStringExtra(EXTRA_STOPDATE);
        List<String> termCourses = i.getStringArrayListExtra(String.valueOf(EXTRA_COURSE_LIST));

        termTitle = findViewById(R.id.editText_term_title);
        termTitle.setText(termTitleExtra);

        termDescription =  (TextView) findViewById(R.id.editText_term_desc);
        termDescription.setText(termDescExtra);

        termStartDate = findViewById(R.id.editText_startdate);
        termStartDate.setText(termStartExtra);

        termStopDate = findViewById(R.id.editText_enddate);
        termStopDate.setText(termStopExtra);

        termCourseList = findViewById(R.id.lv_term_courses);
     //   termCourseList.set something....

    }

}


