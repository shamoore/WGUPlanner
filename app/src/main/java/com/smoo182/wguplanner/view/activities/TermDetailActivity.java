package com.smoo182.wguplanner.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Term;
import com.smoo182.wguplanner.logic.TermDetailViewModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class TermDetailActivity extends BaseSecondaryActivity {

    private static final String EXTRA_TERM_TITLE = "EXTRA_TERM_TITLE";

    private String termTitleExtra;

    private List<Course> listOfCourses;
    private TextView termTitle;
    private TextView termDescription;
    private Button termStartDate;
    private Button termStopDate;
    private RecyclerView termCourseList;
    private TextView zeroState;
    private SubListAdapter adapter;


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
        termTitleExtra = i.getStringExtra(EXTRA_TERM_TITLE);
        termDetailViewModel.getTermByTitle(termTitleExtra).observe(this, new Observer<Term>() {
            @Override
            public void onChanged(@Nullable Term term) {
                if (term != null) {
                    termTitle.setText(term.getTitle());
                    termDescription.setText(term.getDescription());
                    termStartDate.setText(term.getStartDate());
                    termStopDate.setText(term.getEndDate());
                }
            }
        });
        termTitle = findViewById(R.id.editText_term_title);
        termDescription = (TextView) findViewById(R.id.editText_term_desc);
        termStartDate = findViewById(R.id.editText_startdate);
        termStopDate = findViewById(R.id.editText_enddate);
        termCourseList = findViewById(R.id.rv_term_courses);
        zeroState = findViewById(R.id.text_zero_state);


        termStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastActiveButton = termStartDate;
                datePicker(v);

            }
        });

        termStopDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastActiveButton = termStopDate;
                datePicker(v);

            }
        });

        termDetailViewModel.getCoursesByTerm(termTitleExtra).observe(this, new
                Observer<List<Course>>() {

                    @Override
                    public void onChanged(@Nullable List<Course> courses) {
                        if (listOfCourses == null) {
                            setCourseTerms(courses);
                        }
                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Term activeTerm = new Term(
                termTitle.getText().toString(),
                termStartDate.getText().toString(),
                termStopDate.getText().toString(),
                termDescription.getText().toString());


        switch (menuItem.getItemId()) {
            case R.id.action_add:
                if(validate(activeTerm)) {
                    termDetailViewModel.addTerm(activeTerm);
                    if (listOfCourses != null) {
                        for (Course course : listOfCourses) {
                            termDetailViewModel.addCourse(course);
                        }
                    }
                    startTermListActivity();
                    return true;
                }
                return false;
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

    public void setCourseTerms(List<Course> listOfCourses) {

        if (listOfCourses.size() == 0) {
            zeroState.setVisibility(View.VISIBLE);
        } else {

            this.listOfCourses = listOfCourses;
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            termCourseList.setLayoutManager(layoutManager);
            adapter = new SubListAdapter();
            termCourseList.setAdapter(adapter);

            DividerItemDecoration itemDecoration = new DividerItemDecoration(
                    termCourseList.getContext(),
                    layoutManager.getOrientation()
            );

            termCourseList.addItemDecoration(itemDecoration);

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
            Course currentCourse = listOfCourses.get(position);
            holder.subListText.setText(currentCourse.getCode() + ": " + currentCourse.getName());
            if (currentCourse.getTermTitle() == null) {
                holder.toggle.setChecked(false);
                holder.checkmark.setVisibility(View.INVISIBLE);
            } else {
                holder.toggle.setChecked(true);
                holder.checkmark.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return listOfCourses.size();
        }

        class SubListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView subListText;
            private Switch toggle;
            private ViewGroup container;
            private ImageView checkmark;

            public SubListViewHolder(View itemView) {
                super(itemView);
                this.subListText = (TextView) itemView.findViewById(R.id.text_sublist_item);
                this.toggle = (Switch) itemView.findViewById(R.id.termToggle);
                this.checkmark = itemView.findViewById(R.id.checkmark);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_sublist_item);
                this.container.setOnClickListener(this);
            }

            public void onClick(View v) {
                Course listCourse = listOfCourses.get(this.getAdapterPosition());
                if (toggle.isChecked()) {
                    toggle.setChecked(false);
                    checkmark.setVisibility(View.INVISIBLE);
                    listCourse.setTermTitle(null);
                } else {
                    toggle.setChecked(true);
                    checkmark.setVisibility(View.VISIBLE);
                    listCourse.setTermTitle(termTitleExtra);
                }
            }
        }


    }

    @Override
    void returnDate(final Calendar calendar) {
        String formattedDate = dateFormat.format(calendar.getTime());
        if (lastActiveButton == termStartDate) {
            termStartDate.setText(formattedDate);
        } else if (lastActiveButton == termStopDate) {
            termStopDate.setText(formattedDate);
        }
    }

    boolean validate(Term activeTerm){
        boolean valid= true;
        if (activeTerm.getTitle().isEmpty()) {
            termTitle.setError("Required");
            valid = false;
        }
        if (activeTerm.getStartDate().isEmpty()) {
            termStartDate.setError("Required");
            valid = false;
        }
        if (activeTerm.getEndDate().isEmpty()) {
            termStopDate.setError("Required");
            valid = false;
        }
        return valid;
    }

}


