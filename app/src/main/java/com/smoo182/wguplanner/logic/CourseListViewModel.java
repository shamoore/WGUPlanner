package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Course;

import java.util.List;

import javax.inject.Inject;

public class CourseListViewModel extends ViewModel{
    private PlannerRepository plannerRepository;

    @Inject
    CourseListViewModel(PlannerRepository plannerRepository){
        this.plannerRepository = plannerRepository;
    }

    public LiveData<List<Course>> getListOfCourses() { return this.plannerRepository.getListofCourses(); }

}
