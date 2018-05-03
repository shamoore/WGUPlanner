package com.smoo182.wguplanner.dependencyinjection;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.smoo182.wguplanner.data.PlannerDao;
import com.smoo182.wguplanner.data.PlannerDatabase;
import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.logic.CustomViewModelFactory;
import com.smoo182.wguplanner.logic.TermListViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private final PlannerDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(application, PlannerDatabase.class, "PlannerDatabase.db"
        ).build();
    }

    @Provides
    @Singleton
    PlannerRepository providePlannerRepository(PlannerDao plannerDao){
        return new PlannerRepository(plannerDao);
    }

    @Provides
    @Singleton
    PlannerDao providePlannerDao(PlannerDatabase database){
        return database.plannerDao();
    }

    @Provides
    @Singleton
    PlannerDatabase providePlannerDatabase(Application application){
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(PlannerRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
