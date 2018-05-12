package com.smoo182.wguplanner.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.util.StringUtil;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.logic.MentorDetailViewModel;

import java.util.List;

import javax.inject.Inject;

public class MentorDetailActivity extends BaseSecondaryActivity {

    private static final String EXTRA_MENTOR_NAME = "EXTRA_MENTOR_NAME";
    private static final String EXTRA_COURSE_CODE = "EXTRA_COURSE_CODE";
    private String mentorNameExtra;

    private EditText mentorName;
    private EditText mentorEmail;
    private EditText mentorPhone;
    private RecyclerView mentorCourseList;
    private TextView zeroState;

    private List<Course> mentorCourses;
    private SubListAdapter adapter;

    private LayoutInflater layoutInflater;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MentorDetailViewModel mentorDetailViewModel;

    public MentorDetailActivity newInstance(String mentorName) {
        MentorDetailActivity mentorDetailActivity = new MentorDetailActivity();
        Bundle args = new Bundle();
        args.putString(EXTRA_MENTOR_NAME, mentorName);
        return this;
    }


    void populateScreen() {

        ((PlannerApplication) getApplication())
                .getApplicationComponent()
                .inject((MentorDetailActivity) this);

        setContentView(R.layout.activity_mentor_detail);
        Toolbar toolbar = findViewById(R.id.details_toolbar);
        toolbar.setTitle("Mentor Details");
        setSupportActionBar(toolbar);

        mentorCourseList = (RecyclerView) findViewById(R.id.rv_mentor_courses);
        layoutInflater = getLayoutInflater();

        mentorDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get
                (MentorDetailViewModel.class);
        Intent i = getIntent();
        mentorNameExtra = i.getStringExtra(EXTRA_MENTOR_NAME);
        mentorDetailViewModel.getMentorByName(mentorNameExtra).observe(this, new Observer<Mentor>
                () {

            @Override
            public void onChanged(@Nullable Mentor mentor) {
                if (mentor != null) {
                    mentorName.setText(mentor.getName());
                    mentorEmail.setText(mentor.getEmail());
                    mentorPhone.setText(mentor.getPhone());
                }
            }
        });
        mentorName = findViewById(R.id.editText_mentor_title);
        mentorEmail = findViewById(R.id.editText_mentor_email);
        mentorPhone = findViewById(R.id.editText_mentor_phone);
        zeroState = findViewById(R.id.text_zero_state);

        mentorPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        mentorDetailViewModel.getCoursesByMentor(mentorNameExtra).observe(this, new
                Observer<List<Course>>() {
                    @Override
                    public void onChanged(@Nullable List<Course> courses) {
                        if (mentorCourses == null) {
                            setMentorCourses(courses);
                        }
                    }
                });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Mentor activeMentor = new Mentor(
                mentorName.getText().toString(),
                mentorEmail.getText().toString(),
                mentorPhone.getText().toString());


        switch (menuItem.getItemId()) {
            case R.id.action_add:
                if (validate(activeMentor)) {
                    mentorDetailViewModel.addMentor(activeMentor);
                    startMentorListActivity();
                    return true;
                }
                return false;
            case R.id.action_delete:
                mentorDetailViewModel.deleteMentor(activeMentor);
                startMentorListActivity();
                return true;
            default:
                startMentorListActivity();
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private boolean validate(Mentor activeMentor) {
        if (activeMentor.getName().isEmpty()) {
            mentorName.setError("Required");
            return false;
        } else {
            return true;
        }
    }

    private void startMentorListActivity() {
        startActivity(new Intent(this, MentorListActivity.class));
    }

    public void setMentorCourses(List<Course> listOfCourses) {
        if (listOfCourses.size() == 0) {
            zeroState.setVisibility(View.VISIBLE);
        } else {
            this.mentorCourses = listOfCourses;
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mentorCourseList.setLayoutManager(layoutManager);
            adapter = new SubListAdapter();
            mentorCourseList.setAdapter(adapter);

            DividerItemDecoration itemDecoration = new DividerItemDecoration(
                    mentorCourseList.getContext(),
                    layoutManager.getOrientation()
            );

            mentorCourseList.addItemDecoration(itemDecoration);

        }

    }

    public void onClick(View view) {
    }

    private class SubListAdapter extends RecyclerView.Adapter<SubListAdapter.SubListViewHolder> {

        public SubListAdapter.SubListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.sublist_item, parent, false);
            return new SubListViewHolder(v);
        }


        public void onBindViewHolder(SubListAdapter.SubListViewHolder holder, int position) {
            Course currentCourse = mentorCourses.get(position);
            holder.subListText.setText(currentCourse.getCode() + ": " + currentCourse.getName());
        }

        @Override
        public int getItemCount() {
            return mentorCourses.size();
        }

        class SubListViewHolder extends RecyclerView.ViewHolder {
            private TextView subListText;
            private ViewGroup container;

            public SubListViewHolder(View itemView) {
                super(itemView);
                this.subListText = (TextView) itemView.findViewById(R.id.text_sublist_item);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_sublist_item);
            }

        }
    }

}


