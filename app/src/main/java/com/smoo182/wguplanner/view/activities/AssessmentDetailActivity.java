package com.smoo182.wguplanner.view.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.logic.AssessmentDetailViewModel;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class AssessmentDetailActivity extends BaseSecondaryActivity  {

    private static final String EXTRA_ASSESSMENT_NAME = "EXTRA_ASSESSMENT_NAME";

    private String assessmentNameExtra;
    private EditText editTextName;
    private EditText editTextStatus;
    private RadioButton radioPA;
    private RadioButton radioOA;
    private RadioGroup radioGroupType;
    private String[] coursesArray = new String[]{};
    private String assignedCourse;
    AutoCompleteTextView courseCodeSearch;

    private LayoutInflater layoutInflater;
    private ArrayAdapter<String> adapter;

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
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Assessment activeAssessment = new Assessment(
                editTextName.getText().toString(),
                radioOA.isActivated(),
                editTextStatus.getText().toString(),
                courseCodeSearch.getText().toString());

        switch (menuItem.getItemId()) {
            case R.id.action_add:
                if(validate(activeAssessment)) {
                    assessmentDetailViewModel.addAssessment(activeAssessment);
                    startAssessmentListActivity();
                    return true;
                }
                return false;
            case R.id.action_delete:
                assessmentDetailViewModel.deleteAssessment(activeAssessment);
                startAssessmentListActivity();
                return true;
            default:
                startAssessmentListActivity();
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void startAssessmentListActivity() {
        startActivity(new Intent(this, AssessmentListActivity.class));
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

        editTextName = findViewById(R.id.editText_assessment_title);
        editTextStatus = findViewById(R.id.editText_assessment_status);
        courseCodeSearch = findViewById(R.id.actv_assessment_course);
        radioOA = findViewById(R.id.radioButtonOA);
        radioPA = findViewById(R.id.radioButtonPA);
        radioGroupType = findViewById(R.id.radioGropuAssessmentType);

        courseCodeSearch.setFilters(new InputFilter[] { new InputFilter.AllCaps()});
        assessmentDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AssessmentDetailViewModel.class);

        assessmentDetailViewModel.getCourseCodes().observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] strings) {
                if(strings != null){
                    setCourseStrings(strings);
                }
            }

            private void setCourseStrings(String[] courseList) {
                coursesArray = courseList;
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, courseList);
                courseCodeSearch.setThreshold(1);
                courseCodeSearch.setAdapter(adapter);
            }
        });



        Intent i = getIntent();
        assessmentNameExtra = i.getStringExtra(EXTRA_ASSESSMENT_NAME);
        assessmentDetailViewModel.getAssessmentByTitle(assessmentNameExtra).observe(this, new Observer<Assessment>() {
            @Override
            public void onChanged(@Nullable Assessment assessment) {
                if(assessment != null){
                    editTextName.setText(assessment.getName());
                    editTextStatus.setText(assessment.getStatus());
                    if(assessment.getType()){ radioOA.setChecked(true);}
                    else{ radioPA.setChecked(true);}
                    courseCodeSearch.setText(assessment.getCourseCode());
            }
        }

    });


    }


    public boolean validate(Assessment activeAssessment){
        boolean valid = true;
        if(activeAssessment.getName().isEmpty())
        {
            editTextName.setError("Required");
            valid = false;
        }
        if(!radioOA.isChecked() & !radioPA.isChecked())
        {
            radioOA.setError("Type must be selected");
            valid = false;
        }


        if(!Arrays.asList(coursesArray).contains(activeAssessment.getCourseCode()))
        {
            courseCodeSearch.setError("Please Choose a valid course from the list. If no courses are listed, please add some.");
            valid = false;}
        return valid;
    }
}




