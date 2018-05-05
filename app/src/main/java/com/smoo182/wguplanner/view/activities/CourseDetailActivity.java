package com.smoo182.wguplanner.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.logic.CourseDetailViewModel;

import java.util.List;

import javax.inject.Inject;

public class CourseDetailActivity extends BaseSecondaryActivity {

    private static final String EXTRA_COURSE_CODE = "EXTRA_COURSE_CODE";

    private EditText courseCode;
    private EditText courseTitle;
    private EditText courseStartDate;
    private EditText courseStopDate;
    private EditText courseNote;
    private List<Assessment> courseAssessments;

    private LayoutInflater layoutInflater;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CourseDetailViewModel courseDetailViewModel;

    public CourseDetailActivity newInstance(String courseCode){
        CourseDetailActivity courseDetailActivity = new CourseDetailActivity();
        Bundle args = new Bundle();
        args.putString(EXTRA_COURSE_CODE, courseCode);
        return this;
    }

    @Override
    void populateScreen() {

        ((PlannerApplication) getApplication())
                .getApplicationComponent()
                .inject(this);
        layoutInflater = getLayoutInflater();

        setContentView(R.layout.activity_course_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Course Details");
        setSupportActionBar(toolbar);

        courseDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CourseDetailViewModel.class);

        Intent i = getIntent();
        String courseCodeExtra = i.getStringExtra(EXTRA_COURSE_CODE);
        courseDetailViewModel.getCourseByCode(courseCodeExtra).observe(this, new Observer<Course>() {
            @Override
            public void onChanged(@Nullable Course course) {
                if(course != null){
                    courseTitle.setText(course.getName());
                    courseCode.setText(course.getCode());
                    courseStartDate.setText(course.getStartDate());
                    courseStopDate.setText(course.getEndDate());
                    courseNote.setText(course.getNote());
                    //TODO: courseAssessments needs to be handled
                }
            }
        });

        courseCode = findViewById(R.id.editText_course_code);
        courseTitle = findViewById(R.id.editText_course_title);
        courseNote = findViewById(R.id.editText_course_note);
        courseStartDate = findViewById(R.id.editText_startdate);
        courseStopDate = findViewById(R.id.editText_enddate);
        // TODO: courseAssessments = findViewById(R.id.lv_course_assessments);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Course activeCourse = new Course(
                courseCode.getText().toString(),
                courseTitle.getText().toString(),
                courseNote.getText().toString(),
                courseStartDate.getText().toString(),
                courseStopDate.getText().toString());
        //TODO: termCourseList needs populating

        switch (menuItem.getItemId()) {
            case R.id.action_add:

                courseDetailViewModel.addCourse(activeCourse);
                startCourseListActivity();
                return true;
            case R.id.action_delete:
                courseDetailViewModel.deleteCourse(activeCourse);
                startCourseListActivity();
                return true;
            default:
                startCourseListActivity();
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void startCourseListActivity() {
        startActivity(new Intent(this, CourseListActivity.class));
    }
}
