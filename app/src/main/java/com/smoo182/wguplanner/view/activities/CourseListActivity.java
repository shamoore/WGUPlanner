package com.smoo182.wguplanner.view.activities;

import android.app.ActivityOptions;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.logic.CourseListViewModel;

import java.util.List;

import javax.inject.Inject;

public class CourseListActivity extends BasePrimaryActivity {
    private static final String EXTRA_COURSE_CODE = "EXTRA_COURSE_CODE";

    private List<Course> listOfCourses;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    CourseListViewModel courseListViewModel;

    @Override
    int getContentViewId() {
        return R.layout.activity_course_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_courses;
    }

    @Override
    void populateScreen() {
        FloatingActionButton addFab = findViewById(R.id.fab_add_course);
        addFab.setOnClickListener(view -> startDetailActivity(null, view));
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Course List");
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv_course_list);
        layoutInflater = getLayoutInflater();

        ((PlannerApplication) getApplication()).getApplicationComponent().inject(this);
        courseListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CourseListViewModel.class);
        courseListViewModel.getListOfCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(@Nullable List<Course> courses) {
                if (listOfCourses == null) {
                    setListCourses(courses);
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public void startDetailActivity(String courseCode, View viewRoot) {
        Intent i = new Intent(this, CourseDetailActivity.class);
        i.putExtra(EXTRA_COURSE_CODE, courseCode);

        if (courseCode != null) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this,
                            new Pair<View, String>(viewRoot.findViewById(R.id.list_item_title),
                                    "title"),
                            new Pair<View, String>(viewRoot.findViewById(R.id.list_item_subtitle)
                                    , "subtitle"));
            startActivity(i, options.toBundle());
        } else {
            startActivity(i);
        }
    }

    public void setListCourses(List<Course> listOfCourses) {
        this.listOfCourses = listOfCourses;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        recyclerView.addItemDecoration(itemDecoration);
    }

    public void onClick(View view) {
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int
                viewType) {
            View v = layoutInflater.inflate(R.layout.list_item, parent, false);
            return new CustomViewHolder(v);
        }

        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {
            Course currentItem = listOfCourses.get(position);

            holder.title.setText(currentItem.getCode() + ": " + currentItem.getName());
            holder.subTitle.setText("from " + currentItem.getStartDate() + " to " + currentItem.getEndDate());
        }

        @Override
        public int getItemCount() { return listOfCourses.size(); }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView title;
            private TextView subTitle;
            private ViewGroup container;

            public CustomViewHolder(View itemView) {
                super(itemView);
                this.title = (TextView) itemView.findViewById(R.id.list_item_title);
                this.subTitle = (TextView) itemView.findViewById(R.id.list_item_subtitle);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_list_item);
                this.container.setOnClickListener(this);
            }

            public void onClick(View v) {
                Course listCourse = listOfCourses.get(this.getAdapterPosition());
                startDetailActivity(String.valueOf(listCourse.getCode()), v);
            }
        }
    }
}
