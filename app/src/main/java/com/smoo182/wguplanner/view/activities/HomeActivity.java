package com.smoo182.wguplanner.view.activities;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.logic.HomeViewModel;

import javax.inject.Inject;

public class HomeActivity extends BasePrimaryActivity {

    private TextView contentTextView;
    private TextView authorTextView;

    private LayoutInflater layoutInflater;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    HomeViewModel homeViewModel;


    @Override
    int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_home;
    }

    public void populateScreen(){
        contentTextView = (TextView) findViewById(R.id.text_quote);
        authorTextView = (TextView) findViewById(R.id.text_quote_author);
        layoutInflater = getLayoutInflater();

        ((PlannerApplication) getApplication()).getApplicationComponent().inject(this);

        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        homeViewModel.InsertQuotes();
        homeViewModel.getRandomQuote().observe(this, new Observer<Quote>() {
            @Override
            public void onChanged(@Nullable Quote quote) {
                authorTextView.setText(quote.getAuthor());
                contentTextView.setText(quote.getContent());
            }
        });


    }
}
