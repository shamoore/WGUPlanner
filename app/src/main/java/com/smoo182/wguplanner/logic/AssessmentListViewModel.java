package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Assessment;

import java.util.List;

import javax.inject.Inject;

public class AssessmentListViewModel extends ViewModel {
    private PlannerRepository plannerRepository;

    @Inject
    public AssessmentListViewModel(PlannerRepository plannerRepository){
        this.plannerRepository = plannerRepository;
    }

    public LiveData<List<Assessment>> getListOfAssessments() { return this.plannerRepository.getListofAssessments(); }
}
