package com.smoo182.wguplanner.dependencyinjection;

import android.app.Application;

import com.smoo182.wguplanner.PlannerApplication;

import dagger.Provides;

public class ApplicationModule {

    private final PlannerApplication application;

    public ApplicationModule(PlannerApplication application){
        this.application = application;
    }

    @Provides
    PlannerApplication providePlannerApplication(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }

}
