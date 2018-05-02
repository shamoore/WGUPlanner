package com.smoo182.wguplanner.view.activities;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.ListItem;
import com.smoo182.wguplanner.data.datatypes.Term;
import com.smoo182.wguplanner.view.interfaces.ListViewInterface;

import java.util.List;

public class TermDetailActivity extends BaseSecondaryActivity implements ListViewInterface{

    private static final String EXTRA_TERM_ID = "EXTRA_TERM_ID";

    private TextView termTitle;
    private TextView termDescription;
    private TextView termStartDate;
    private TextView termStopDate;
    private ListView termCourseList;

    private LayoutInflater layoutInflater;





    @Override
    void populateScreen() {
        layoutInflater = getLayoutInflater();


        setContentView(R.layout.activity_term_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Term Details");
        setSupportActionBar(toolbar);

        Intent i = getIntent();
     //   int termTitleExtra=  i.getIntExtra(EXTRA_TERM_ID);


        termTitle = findViewById(R.id.editText_term_title);
    //    termTitle.setText(termTitleExtra);

        termDescription =  (TextView) findViewById(R.id.editText_term_desc);
       // termDescription.setText(termDescExtra);

        termStartDate = findViewById(R.id.editText_startdate);
      //  termStartDate.setText(termStartExtra);

        termStopDate = findViewById(R.id.editText_enddate);
      //  termStopDate.setText(termStopExtra);

        termCourseList = findViewById(R.id.lv_term_courses);
     //   termCourseList.set something....

    }

    @Override
    public void startDetailActivity(String title, View viewRoot) {

    }

    @Override
    public void setUpAdapterAndView(List<ListItem> listOfData) {

    }

    @Override
    public void addNewListItemToView(Term newItem) {

    }

    @Override
    public void deleteListItemAt(int position) {

    }

    @Override
    public void insertListItemAt(int temporaryListItemPosition, ListItem temporaryListItem) {

    }
}


