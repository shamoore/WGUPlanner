package com.smoo182.wguplanner.view.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.PlannerDao;
import com.smoo182.wguplanner.data.datatypes.ListItem;
import com.smoo182.wguplanner.data.datatypes.Term;
import com.smoo182.wguplanner.logic.TermDetailViewModel;
import com.smoo182.wguplanner.view.interfaces.ListViewInterface;

import java.util.List;

import javax.inject.Inject;

public class TermDetailActivity extends BaseSecondaryActivity implements ListViewInterface{

    private static final String EXTRA_TERM_ID = "EXTRA_TERM_ID";
    private String termId;
    private Term term;

    private TextView termTitle;
    private TextView termDescription;
    private TextView termStartDate;
    private TextView termStopDate;
    private ListView termCourseList;

    private LayoutInflater layoutInflater;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private TermDetailViewModel termDetailViewModel;


    public TermDetailActivity newInstance(String termId) {
        TermDetailActivity termDetailActivity = new TermDetailActivity();
        Bundle args = new Bundle();
        args.putString(EXTRA_TERM_ID, termId);
        return this;
    }


    @Override
    void populateScreen() {

        ((PlannerApplication) getApplication())
                .getApplicationComponent()
                .inject((TermDetailActivity) this);

        Bundle args = getArguments();

        this.termId = args.getString(EXTRA_TERM_ID);

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


