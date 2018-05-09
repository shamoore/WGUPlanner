package com.smoo182.wguplanner.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.data.datatypes.MentorAssignment;
import com.smoo182.wguplanner.data.datatypes.MentorCourses;
import com.smoo182.wguplanner.logic.CourseDetailViewModel;

import java.util.List;

import javax.inject.Inject;

public class CourseDetailActivity extends BaseSecondaryActivity {

    private static final String EXTRA_COURSE_CODE = "EXTRA_COURSE_CODE";

    private EditText courseCode;
    private EditText courseTitle;
    private Button courseStartDate;
    private Button courseStopDate;
    private EditText courseNote;
    private RecyclerView courseAssessments;
    private RecyclerView courseMentors;

    public List<Assessment> listOfAssessments;
    public List<MentorAssignment> listOfMentors;

    private TextView zeroStateAssessments;
    private TextView zeroStateMentors;
    private Button shareNotes;

    private LayoutInflater layoutInflater;
    private AssessmentListAdapter assessmentListAdapter;
    private MentorListAdapter mentorListAdapter;

    private String courseCodeExtra ="";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CourseDetailViewModel courseDetailViewModel;

    public CourseDetailActivity newInstance(String courseCode) {
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
        courseCodeExtra = i.getStringExtra(EXTRA_COURSE_CODE);
        courseDetailViewModel.getCourseByCode(courseCodeExtra).observe(this, new Observer<Course>() {
            @Override
            public void onChanged(@Nullable Course course) {
                if (course != null) {
                    courseTitle.setText(course.getName());
                    courseCode.setText(course.getCode());
                    courseStartDate.setText(course.getStartDate());
                    courseStopDate.setText(course.getEndDate());
                    courseNote.setText(course.getNote());
                }
            }
        });

        courseCode = findViewById(R.id.editText_course_code);
        courseTitle = findViewById(R.id.editText_course_title);
        courseNote = findViewById(R.id.editText_course_note);
        courseStartDate = findViewById(R.id.editText_startdate);
        courseStopDate = findViewById(R.id.editText_enddate);
        courseAssessments = findViewById(R.id.rv_course_assessments);
        courseMentors = findViewById(R.id.rv_course_mentors);
        zeroStateAssessments = findViewById(R.id.text_no_assessments);
        zeroStateMentors = findViewById(R.id.text_no_mentors);
        shareNotes = findViewById(R.id.button_share);

        courseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastActiveButton = courseStartDate;
                datePicker(v);
            }
        });

        courseStopDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastActiveButton = courseStopDate;
                datePicker(v);
            }
        });

        shareNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                String shareString = "Course Notes for" + courseCode.getText().toString() + ": " + courseTitle.getText().toString() + "\r\n" + courseNote.getText().toString();
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareString);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, courseCode.getText().toString() + " Notes");
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(shareIntent, "Share Notes"));
            }
        });

        courseDetailViewModel.getMentorsByCourse(courseCodeExtra).observe(this, new Observer<List<MentorAssignment>>() {
            @Override
            public void onChanged(@Nullable List<MentorAssignment> mentors) {
                if(listOfMentors == null) {
                    setCourseMentors(mentors);
                }
            }
        });
        courseDetailViewModel.getAssessmentsByCourse(courseCodeExtra).observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(@Nullable List<Assessment> assessments) {
                if (listOfAssessments == null) {
                    setCourseAssessments(assessments);
                }
            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Course activeCourse = new Course(
                courseCode.getText().toString(),
                courseTitle.getText().toString(),
                courseNote.getText().toString(),
                courseStartDate.getText().toString(),
                courseStopDate.getText().toString());

        switch (menuItem.getItemId()) {
            case R.id.action_add:

                courseDetailViewModel.addCourse(activeCourse);

                if(listOfMentors.size()>0){
                for (MentorAssignment mentorAssignment: listOfMentors ) {

                    if(mentorAssignment.getCourseCode() != null){
                    courseDetailViewModel.assignMentorToCourse(mentorAssignment);}
//TODO: ELse if we're trying to delete a mentor/course relationship .. fucking delete it.
                }
                }

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


    private void setCourseAssessments(List<Assessment> assessments) {
        if (assessments.size() == 0) {
            zeroStateAssessments.setVisibility(View.VISIBLE);
        } else {
            this.listOfAssessments = assessments;
            LinearLayoutManager assessmentsLayoutManager = new LinearLayoutManager(this);
            courseAssessments.setLayoutManager(assessmentsLayoutManager);
            assessmentListAdapter = new AssessmentListAdapter();
            courseAssessments.setAdapter(assessmentListAdapter);

            DividerItemDecoration itemDecoration = new DividerItemDecoration(
                    courseAssessments.getContext(),
                    assessmentsLayoutManager.getOrientation()
            );

            courseAssessments.addItemDecoration(itemDecoration);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
            itemTouchHelper.attachToRecyclerView(courseAssessments);
        }
    }

    private void setCourseMentors(List<MentorAssignment> mentors){
        if (mentors.size() == 0) {
            zeroStateMentors.setVisibility(View.VISIBLE);
        } else {
            this.listOfMentors = mentors;
            LinearLayoutManager mentorLayoutManager = new LinearLayoutManager(this);
            courseMentors.setLayoutManager(mentorLayoutManager);
            mentorListAdapter = new MentorListAdapter();
            courseMentors.setAdapter(mentorListAdapter);

            DividerItemDecoration itemDecoration = new DividerItemDecoration(
                    courseMentors.getContext(),
                    mentorLayoutManager.getOrientation()
            );

            courseMentors.addItemDecoration(itemDecoration);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
            itemTouchHelper.attachToRecyclerView(courseMentors);
        }
    }







    private class AssessmentListAdapter extends RecyclerView.Adapter<AssessmentListAdapter.AssessmentListViewHolder> {

        public AssessmentListAdapter.AssessmentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.sublist_item, parent, false);
            return new AssessmentListViewHolder(v);
        }

        public void onBindViewHolder(@NonNull AssessmentListAdapter.AssessmentListViewHolder holder, int position) {
            Assessment currentAssessment = listOfAssessments.get(position);

            String type = "OA";
            if (!currentAssessment.getType()) {
                type = "PA";
            }

            holder.subListText.setText(type + ": " + currentAssessment.getName());
            if (currentAssessment.getCourseCode() == null) {
                holder.toggle.setChecked(false);
                holder.checkmark.setVisibility(View.INVISIBLE);
            } else {
                holder.toggle.setChecked(true);
                holder.checkmark.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return listOfAssessments.size();
        }

        class AssessmentListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView subListText;
            private Switch toggle;
            private ViewGroup container;
            private ImageView checkmark;

            public AssessmentListViewHolder(View itemView) {
                super(itemView);
                this.subListText = (TextView) itemView.findViewById(R.id.text_sublist_item);
                this.toggle = (Switch) itemView.findViewById(R.id.termToggle);
                this.checkmark = itemView.findViewById(R.id.checkmark);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_sublist_item);
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Assessment listAssessment = listOfAssessments.get(this.getAdapterPosition());
                if (toggle.isChecked()) {
                    toggle.setChecked(false);
                    checkmark.setVisibility(View.INVISIBLE);
                    listAssessment.setCourseCode(courseCodeExtra);

                } else {
                    toggle.setChecked(true);
                    checkmark.setVisibility(View.VISIBLE);
                    listAssessment.setCourseCode(courseCodeExtra);
                }
            }
        }
    }


    private class MentorListAdapter extends RecyclerView.Adapter<MentorListAdapter.MentorListViewHolder> {

        public MentorListAdapter.MentorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.sublist_item, parent, false);
            return new MentorListViewHolder(v);
        }

        public void onBindViewHolder(@NonNull MentorListAdapter.MentorListViewHolder holder, int position) {
            MentorAssignment currentMentor = listOfMentors.get(position);


            holder.subListText.setText(currentMentor.getName() + "\nE: " + currentMentor.getEmail() + "\nP: "+ currentMentor.getPhone());
            if (currentMentor.getCourseCode() != null) {
                holder.toggle.setChecked(true);
                holder.checkmark.setVisibility(View.VISIBLE);
            } else {
                holder.toggle.setChecked(false);
                holder.checkmark.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return listOfMentors.size();
        }

        class MentorListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView subListText;
            private Switch toggle;
            private ViewGroup container;
            private ImageView checkmark;

            public MentorListViewHolder(View itemView) {
                super(itemView);
                this.subListText = (TextView) itemView.findViewById(R.id.text_sublist_item);
                this.toggle = (Switch) itemView.findViewById(R.id.termToggle);
                this.checkmark = itemView.findViewById(R.id.checkmark);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_sublist_item);
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                MentorAssignment listMentor = listOfMentors.get(this.getAdapterPosition());
                if (toggle.isChecked()) {
                    toggle.setChecked(false);
                    checkmark.setVisibility(View.INVISIBLE);
                    listMentor.setCourseCode(null);
                } else {
                    toggle.setChecked(true);
                    checkmark.setVisibility(View.VISIBLE);
                    listMentor.setCourseCode(courseCodeExtra);

                }
            }
        }
    }

    private void startCourseListActivity() {
        startActivity(new Intent(this, CourseListActivity.class));
    }
}

