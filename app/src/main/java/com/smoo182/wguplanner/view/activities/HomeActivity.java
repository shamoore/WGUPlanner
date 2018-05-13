package com.smoo182.wguplanner.view.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smoo182.wguplanner.PlannerApplication;
import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.data.datatypes.Reminder;
import com.smoo182.wguplanner.logic.HomeViewModel;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BasePrimaryActivity {

    private TextView contentTextView;
    private TextView authorTextView;
    private Button demoData;
    private List<Reminder> todaysReminders;

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
        ((PlannerApplication) getApplication()).getApplicationComponent().inject(this);

        contentTextView = (TextView) findViewById(R.id.text_quote);
        authorTextView = (TextView) findViewById(R.id.text_quote_author);
        layoutInflater = getLayoutInflater();
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        homeViewModel.InsertQuotes();
        homeViewModel.getRandomQuote().observe(this, new Observer<Quote>() {
            @Override
            public void onChanged(@Nullable Quote quote) {
                authorTextView.setText(quote.getAuthor());
                contentTextView.setText(quote.getContent());
            }
        });

        homeViewModel.getTodaysReminders().observe(this, new Observer<List<Reminder>>() {

            @Override
            public void onChanged(@Nullable List<Reminder> reminders) {
                if(reminders!= null){
                    setTodaysReminders(reminders);
                }
            }

            private void setTodaysReminders(List<Reminder> reminders) {
                String message = "\r\n";
                todaysReminders = reminders;
                for (Reminder reminder: todaysReminders)
                      { message = message + "\r\n" + reminder.getType() + ": " + reminder.getContent();
                }
                if(todaysReminders.size()>0){
                    alertView(message);
                }
                }


        });


    }

    private void alertView(String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        String dialogSubString = " Reminders";

        if(todaysReminders.size() ==1){ dialogSubString = " Reminder";}
        dialog.setTitle("You have " + todaysReminders.size()+ dialogSubString + " for Today")
                .setMessage(message)
                .setNegativeButton("Clear Reminders", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Reminder reminder: todaysReminders) {
                            homeViewModel.DeleteReminders(reminder);
                        }
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();


}}
