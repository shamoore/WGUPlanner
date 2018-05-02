package com.smoo182.wguplanner.view.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.FakeDataSource;
import com.smoo182.wguplanner.data.datatypes.ListItem;
import com.smoo182.wguplanner.data.datatypes.Term;
import com.smoo182.wguplanner.logic.TermsController;
import com.smoo182.wguplanner.view.interfaces.ListViewInterface;

import java.util.List;


public class TermListActivity extends BasePrimaryActivity implements ListViewInterface, View.OnClickListener {
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_SUBTITLE = "EXTRA_SUBTITLE";

    private List<ListItem> listOfData;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private TermsController controller;


    @Override
    int getContentViewId() {
        return R.layout.activity_term_list;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_terms;
    }

    @Override
    void populateScreen() {
        FloatingActionButton addFab = findViewById(R.id.fab_add_term);
        addFab.setOnClickListener(addFabListener);

        recyclerView = (RecyclerView) findViewById(R.id.rv_term_list);
        layoutInflater = getLayoutInflater();

        controller = new TermsController(this, new FakeDataSource());
    }

    private View.OnClickListener addFabListener = v -> startActivity(new Intent(TermListActivity.this, TermDetailActivity.class));


    @Override
    public void startDetailActivity(String title, View viewRoot) {
        Intent i = new Intent(this, TermDetailActivity.class);
        i.putExtra(EXTRA_TITLE, title);


        ActivityOptions options = ActivityOptions
                .makeSceneTransitionAnimation(this,
                new Pair<View, String>(viewRoot.findViewById(R.id.list_item_title), "title"));
        startActivity(i, options.toBundle());
    }


    @Override
    public void setUpAdapterAndView(List<ListItem> listOfData) {
        this.listOfData = listOfData;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void addNewListItemToView(Term newItem) {
        ListItem listtem = new ListItem(newItem.getTitle(),  newItem.getStartDate() + " - " + newItem.getStopDate());
        listOfData.add(listtem);
        int endOfList = listOfData.size() - 1;
        adapter.notifyItemInserted(endOfList);
        recyclerView.smoothScrollToPosition(endOfList);

    }

    @Override
    public void deleteListItemAt(int position) {
        listOfData.remove(position);
        adapter.notifyItemRemoved(position);

    }


    @Override
    public void insertListItemAt(int temporaryListItemPosition, ListItem temporaryListItem) {
        listOfData.add(temporaryListItemPosition, temporaryListItem);
        adapter.notifyItemInserted(temporaryListItemPosition);

    }

    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.fab_add_term) {
            controller.createNewListItem();
        }
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.list_item_term, parent, false);
            return new CustomViewHolder(v);
        }

        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {
            ListItem currentItem = listOfData.get(position);
            holder.title.setText(currentItem.getTitle());
            holder.subTitle.setText(currentItem.getSubtitle());
        }

        @Override
        public int getItemCount() {
            return listOfData.size();
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
                ListItem listItem = listOfData.get(this.getAdapterPosition());
                controller.onListItemClick(listItem, v);
            }
        }
    }

    private ItemTouchHelper.Callback createHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleItemCouchCallback  = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                controller.onListItemSwiped(
                        position, listOfData.get(position));

            }
        };
        return simpleItemCouchCallback;
    }

}
