package com.smoo182.wguplanner.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.smoo182.wguplanner.R;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        populateScreen();
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        if(navigationView != null) {
            navigationView.setOnNavigationItemSelectedListener(this);
        }
    }


    @Override
    protected void onStart(){
        super.onStart();
        updateNavigationBarState();

    }

    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(0,0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(()->{
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.action_home:
                    startActivity(new Intent(this, HomeActivity.class));
                    break;
                    case R.id.action_terms:
                    startActivity(new Intent(this, TermListActivity.class));
                    break;
                    case R.id.action_courses:
                    startActivity(new Intent(this, CourseListActivity.class));
                    break;
                    case R.id.action_assessments:
                    startActivity(new Intent(this, AssessmentListActivity.class));
                    break;
                    case R.id.action_mentors:
                    startActivity(new Intent(this, MentorListActivity.class));
                    break;
            }
            finish();

        }, 300);
        return true;
    }

    private void updateNavigationBarState(){
        int actionID = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionID);

    }

    void selectBottomNavigationBarItem(int itemId){
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    abstract int getContentViewId();
    abstract int getNavigationMenuItemId();
    abstract void populateScreen();


}
