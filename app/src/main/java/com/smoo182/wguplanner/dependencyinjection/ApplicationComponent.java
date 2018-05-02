package com.smoo182.wguplanner.dependencyinjection;


import android.app.Application;

import com.smoo182.wguplanner.view.activities.AssessmentDetailActivity;
import com.smoo182.wguplanner.view.activities.AssessmentListActivity;
import com.smoo182.wguplanner.view.activities.CourseDetailActivity;
import com.smoo182.wguplanner.view.activities.CourseListActivity;
import com.smoo182.wguplanner.view.activities.HomeActivity;
import com.smoo182.wguplanner.view.activities.MentorDetailActivity;
import com.smoo182.wguplanner.view.activities.MentorListActivity;
import com.smoo182.wguplanner.view.activities.TermDetailActivity;
import com.smoo182.wguplanner.view.activities.TermListActivity;

import javax.inject.Singleton;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

@Singleton
@Component(modules= { ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {
    void inject (AssessmentDetailActivity assessmentDetailActivity);
    void inject (AssessmentListActivity assessmentListActivity);
    void inject (CourseDetailActivity courseDetailActivity);
    void inject (CourseListActivity courseListActivity);
    void inject (HomeActivity homeActivity);
    void inject (MentorDetailActivity mentorDetailActivity);
    void inject (MentorListActivity mentorListActivity);
    void inject (TermDetailActivity termDetailActivity);
    void inject (TermListActivity termListActivity);

    Application application();
}
