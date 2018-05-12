package com.smoo182.wguplanner.view.activities;

import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.logic.AssessmentListViewModel;

import java.util.List;

import javax.inject.Inject;

public class AssessmentListActivity extends BasePrimaryActivity {
    private static final String EXTRA_ASSESSMENT_NAME = "EXTRA_ASSESSMENT_NAME";
    private List<Assessment> listOfAssessments;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Inject
    ViewModelProvider.Factory viewModelfactory;
    AssessmentListViewModel assessmentListViewModel;

    @Override
    int getContentViewId() {
        return R.layout.activity_assessment_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_assessments;
    }

    @Override
    void populateScreen() {
        FloatingActionButton addFab = findViewById(R.id.fab_add_assessment);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AssessmentListActivity.this, AssessmentDetailActivity.class));
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Assessment List");
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv_assessment_list);
        layoutInflater = getLayoutInflater();

        ((PlannerApplication) getApplication()).getApplicationComponent().inject(this);
        assessmentListViewModel = ViewModelProviders.of(this, viewModelfactory).get(AssessmentListViewModel.class);
        assessmentListViewModel.getListOfAssessments().observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(@Nullable List<Assessment> assessments) {
                if(listOfAssessments == null){
                    setListOfAssessments(assessments);
                }
            }
        });


    }

public void startDetailActivity(String assessmentTitle, View viewRot){
        Intent i = new Intent(this, AssessmentDetailActivity.class);
        i.putExtra(EXTRA_ASSESSMENT_NAME, assessmentTitle);
        if(assessmentTitle != null){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                    new Pair<View, String>(viewRot.findViewById(R.id.list_item_title),
                            "title"),
                    new Pair<View, String>(viewRot.findViewById(R.id.list_item_subtitle),
                            "subtitle"));
            startActivity(i, options.toBundle());
        }
        else
            startActivity(i);
}

public void setListOfAssessments(List<Assessment> listAssessments){
        this.listOfAssessments = listAssessments;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    adapter = new CustomAdapter();
    recyclerView.setAdapter(adapter);

    DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
    recyclerView.addItemDecoration(itemDecoration);
}

public void onClick(View view){}

private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        Assessment currentAssessment = listOfAssessments.get(position);
        holder.title.setText(currentAssessment.getName());
        holder.subTitle.setText(assessmentType(currentAssessment.getType()) + " for " + currentAssessment.getCourseCode());

    }

    @Override
    public int getItemCount() {
        return listOfAssessments.size();
    }

    private String assessmentType(boolean type){
        if(type) return "PA";
        else return "OA";
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView subTitle;
        private ViewGroup container;

        public CustomViewHolder(View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.list_item_title);
            this.subTitle = itemView.findViewById(R.id.list_item_subtitle);
            this.container = itemView.findViewById(R.id.root_list_item);
            this.container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Assessment listAssessment = listOfAssessments.get(this.getAdapterPosition());
            startDetailActivity(String.valueOf(listAssessment.getName()), v);

        }
    }
}


}
