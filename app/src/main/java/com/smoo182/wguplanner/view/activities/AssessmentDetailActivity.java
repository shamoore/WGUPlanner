package com.smoo182.wguplanner.view.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.logic.AssessmentDetailViewModel;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AssessmentDetailActivity extends BaseSecondaryActivity {

    private static final String EXTRA_ASSESSMENT_NAME = "EXTRA_ASSESSMENT_NAME";

    private String assessmentNameExtra;
    private EditText editTextName;
    private EditText editTextStatus;
    private RadioButton radioPA;
    private RadioButton radioOA;
    private RadioGroup radioGroupType;
    private EditText editTextCourseCode;
    String[] courseList;

    private LayoutInflater layoutInflater;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private AssessmentDetailViewModel assessmentDetailViewModel;

    public AssessmentDetailActivity newInstance(String assessmentName){
        AssessmentDetailActivity assessmentDetailActivity = new AssessmentDetailActivity();
        Bundle args = new Bundle();
        args.putString(EXTRA_ASSESSMENT_NAME, assessmentName);
        return this;
    }

    @Override
    void populateScreen() {

        ((PlannerApplication) getApplication())
                .getApplicationComponent()
                .inject(this);

        layoutInflater = getLayoutInflater();
        setContentView(R.layout.activity_assessment_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Assessment Details");
        setSupportActionBar(toolbar);

        assessmentDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AssessmentDetailViewModel.class);

        courseList= assessmentDetailViewModel.getCourseCodes().getValue();

        Intent i = getIntent();
        assessmentNameExtra = i.getStringExtra(EXTRA_ASSESSMENT_NAME);
        assessmentDetailViewModel.getAssessmentByTitle(assessmentNameExtra).observe(this, new Observer<Assessment>() {
            @Override
            public void onChanged(@Nullable Assessment assessment) {
                if(assessment != null){
                    editTextName.setText(assessment.getName());
                    editTextCourseCode.setText(assessment.getCourseCode());
                    editTextStatus.setText(assessment.getStatus());
                    if(assessment.getType()){ radioOA.toggle();}
                    else{ radioPA.toggle(); }
            }
        }

    });

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (getParent(), android.R.layout.select_dialog_item, courseList);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.actv_assessment_course);
        actv.setThreshold(1);
        actv.setAdapter(adapter);

    }



}




