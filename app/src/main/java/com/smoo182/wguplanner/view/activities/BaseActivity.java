package com.smoo182.wguplanner.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.smoo182.wguplanner.R;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    //@Override
    protected void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
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
        selectBottomNavigationBarItem(getNavigationMenuItemId());

    }

    void selectBottomNavigationBarItem(int itemId){
        navigationView.getMenu().findItem(itemId).setChecked(true);
    }

    abstract int getContentViewId();
    abstract int getNavigationMenuItemId();


}
