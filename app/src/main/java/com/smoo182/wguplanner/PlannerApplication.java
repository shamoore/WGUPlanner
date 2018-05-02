package com.smoo182.wguplanner;

import android.app.Application;

import com.smoo182.wguplanner.dependencyinjection.ApplicationComponent;
import com.smoo182.wguplanner.dependencyinjection.ApplicationModule;
import com.smoo182.wguplanner.dependencyinjection.RoomModule;

public class PlannerApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
             return applicationComponent;
        }
    }

