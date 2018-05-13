package com.smoo182.wguplanner.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.logic.AssessmentDetailViewModel;

import java.util.Arrays;

import javax.inject.Inject;

public class AssessmentDetailActivity extends BaseSecondaryActivity {

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
    public Button goalDate;
    public CheckBox reminder;
    private Assessment originalAssessment;

    private LayoutInflater layoutInflater;
    private ArrayAdapter<String> adapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private AssessmentDetailViewModel assessmentDetailViewModel;

    public AssessmentDetailActivity newInstance(String assessmentName) {
        AssessmentDetailActivity assessmentDetailActivity = new AssessmentDetailActivity();
        Bundle args = new Bundle();
        args.putString(EXTRA_ASSESSMENT_NAME, assessmentName);
        return this;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Assessment activeAssessment = new Assessment(
                editTextName.getText().toString(),
                radioOA.isChecked(),
                editTextStatus.getText().toString(),
                courseCodeSearch.getText().toString(),
                goalDate.getText().toString(),
                reminder.isChecked());

        switch (menuItem.getItemId()) {
            case R.id.action_add:
                if (validate(activeAssessment)) {
                    assessmentDetailViewModel.addAssessment(activeAssessment);

                    ///Because I'm using the name as the primary key, I do not want to create duplicates when I actually intended to edit the original.
                    //If I have an original name, and its not the same as my new name, delete my original and put my new one in its place.
                    if (originalAssessment != null && !originalAssessment.getName().equals(activeAssessment.getName())) {
                        assessmentDetailViewModel.deleteAssessment(originalAssessment);
                    }

                    if(reminder.isChecked()){
                    setReminder(activeAssessment);}
                    else{
                        removeReminder(activeAssessment);
                    }
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

    private void removeReminder(Assessment activeAssessment) {
        assessmentDetailViewModel.removeAssessmentReminder(activeAssessment);
    }

    private void setReminder(Assessment activeAssessment) {

        assessmentDetailViewModel.addAssessmentReminder(activeAssessment);
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
        goalDate = findViewById(R.id.button_goal_date);
        reminder = findViewById(R.id.checkbox_remind);


        goalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastActiveButton = goalDate;
                datePicker(v);
            }
        });

        courseCodeSearch.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        assessmentDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AssessmentDetailViewModel.class);

        assessmentDetailViewModel.getCourseCodes().observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] strings) {
                if (strings != null) {
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
                if (assessment != null) {
                    editTextName.setText(assessment.getName());
                    editTextStatus.setText(assessment.getStatus());
                    if (assessment.getType()) {
                        radioOA.setChecked(true);
                    } else {
                        radioPA.setChecked(true);
                    }
                    courseCodeSearch.setText(assessment.getCourseCode());
                    goalDate.setText(assessment.getGoalDate());
                    reminder.setChecked(assessment.isReminderSet());
                    originalAssessment = assessment;
                }
            }

        });
    }

    public boolean validate(Assessment activeAssessment) {
        boolean valid = true;
        if (activeAssessment.getName().isEmpty()) {
            editTextName.setError("Required");
            valid = false;
        }
        if (!radioOA.isChecked() & !radioPA.isChecked()) {
            radioOA.setError("Type must be selected");
            valid = false;
        }
        if (!Arrays.asList(coursesArray).contains(activeAssessment.getCourseCode())) {
            courseCodeSearch.setError("Please Choose a valid course from the list. If no courses are listed, please add some.");
            valid = false;
        }
        if(reminder.isChecked()){
           if(activeAssessment.getGoalDate().isEmpty()) {
                reminder.setError("Goal Date must be set for a reminder to be set.");
                valid = false;
           }
           }
        return valid;
    }
}




