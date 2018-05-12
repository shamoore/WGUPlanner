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
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.logic.MentorListViewModel;

import java.util.List;

import javax.inject.Inject;

public class MentorListActivity extends BasePrimaryActivity {
    private static final String EXTRA_MENTOR_NAME = "EXTRA_MENTOR_NAME";

    private List<Mentor> listOfMentors;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    MentorListViewModel mentorListViewModel;


    @Override
    int getContentViewId() {
        return R.layout.activity_mentor_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_mentors;
    }

    @Override
    void populateScreen() {
        FloatingActionButton addFab = findViewById(R.id.fab_add_mentor);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MentorListActivity.this, MentorDetailActivity.class));
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        toolbar.setTitle("Mentor List");
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv_mentor_list);
        layoutInflater = getLayoutInflater();

        ((PlannerApplication) getApplication()).getApplicationComponent().inject(this);
        mentorListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MentorListViewModel.class);
        mentorListViewModel.getListOfMentors().observe(this, new Observer<List<Mentor>>() {
            @Override
            public void onChanged(@Nullable List<Mentor> mentors) {
                if (listOfMentors == null) {
                    setListMentors(mentors);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void startDetailActivity(String mentorName, View viewRoot) {
        Intent i = new Intent(this, MentorDetailActivity.class);
        i.putExtra(EXTRA_MENTOR_NAME, mentorName);
        if (mentorName != null) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                    new Pair<View, String>(viewRoot.findViewById(R.id.list_item_title),
                            "title"),
                    new Pair<View, String>(viewRoot.findViewById(R.id.list_item_subtitle),
                            "subtitle"));

            startActivity(i, options.toBundle());
        } else {
            startActivity(i);
        }
    }


    public void setListMentors(List<Mentor> listMentors){
        this.listOfMentors = listMentors;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        recyclerView.addItemDecoration(itemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
            Mentor currentMentor = listOfMentors.get(position);
            holder.title.setText(currentMentor.getName());
            holder.subTitle.setText("Email: " + currentMentor.getEmail() +", Phone: "+ currentMentor.getPhone());
        }

        @Override
        public int getItemCount() {
            return listOfMentors.size();
        }


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
                Mentor listMentor = listOfMentors.get(this.getAdapterPosition());
                startDetailActivity(String.valueOf(listMentor.getName()), v);
            }
        }
    }



}
