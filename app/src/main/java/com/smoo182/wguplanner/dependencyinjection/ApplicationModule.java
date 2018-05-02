package com.smoo182.wguplanner.dependencyinjection;

import android.app.Application;

import com.smoo182.wguplanner.PlannerApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class ApplicationModule {

    private final PlannerApplication application;
    public ApplicationModule(PlannerApplication application){
        this.application = application;
    }

    @Provides
    @Singleton
    PlannerApplication providePlannerApplication(){
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

}
