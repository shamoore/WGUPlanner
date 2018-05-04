package com.smoo182.wguplanner.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Term;
import com.smoo182.wguplanner.logic.TermDetailViewModel;

import javax.inject.Inject;

public class TermDetailActivity extends BaseSecondaryActivity {

    private static final String EXTRA_TERM_TITLE = "EXTRA_TERM_TITLE";

    private TextView termTitle;
    private TextView termDescription;
    private TextView termStartDate;
    private TextView termStopDate;
    private ListView termCourseList;


    private LayoutInflater layoutInflater;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private TermDetailViewModel termDetailViewModel;


    public TermDetailActivity newInstance(String termTitle) {
        TermDetailActivity termDetailActivity = new TermDetailActivity();
        Bundle args = new Bundle();
        args.putString(EXTRA_TERM_TITLE, termTitle);
        return this;
    }


    @Override
    void populateScreen() {

        ((PlannerApplication) getApplication())
                .getApplicationComponent()
                .inject((TermDetailActivity) this);

        layoutInflater = getLayoutInflater();
        setContentView(R.layout.activity_term_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Term Details");
        setSupportActionBar(toolbar);

        termDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TermDetailViewModel.class);

        Intent i = getIntent();
        String termTitleExtra = i.getStringExtra(EXTRA_TERM_TITLE);
        termDetailViewModel.getTermByTitle(termTitleExtra).observe(this, new Observer<Term>() {
            @Override
            public void onChanged(@Nullable Term term) {
                if(term != null) {
                    termTitle.setText(term.getTitle());
                    termDescription.setText(term.getDescription());
                    termStartDate.setText(term.getStartDate());
                    termStopDate.setText(term.getEndDate());
                    //TODO: termCourseList needs population
                }
            }
        });
        termTitle = findViewById(R.id.editText_term_title);
        termDescription = (TextView) findViewById(R.id.editText_term_desc);
        termStartDate = findViewById(R.id.editText_startdate);
        termStopDate = findViewById(R.id.editText_enddate);
        termCourseList = findViewById(R.id.lv_term_courses);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Term activeTerm = new Term(
                termTitle.getText().toString(),
                termStartDate.getText().toString(),
                termStopDate.getText().toString(),
                termDescription.getText().toString());
        //TODO: termCourseList needs populating

        switch (menuItem.getItemId()) {
            case R.id.action_add:

                termDetailViewModel.addTerm(activeTerm);
                startTermListActivity();
                return true;
            case R.id.action_delete:
                termDetailViewModel.deleteTerm(activeTerm);
                startTermListActivity();
                return true;
            default:
                startTermListActivity();
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void startTermListActivity() {
        startActivity(new Intent(this, TermListActivity.class));
    }


}


