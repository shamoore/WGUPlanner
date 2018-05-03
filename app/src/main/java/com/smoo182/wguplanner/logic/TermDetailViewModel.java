package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Term;

import javax.inject.Inject;

public class TermDetailViewModel extends ViewModel {

    private PlannerRepository plannerRepository;

    @Inject
    TermDetailViewModel(PlannerRepository plannerRepository){
        this.plannerRepository = plannerRepository;
    }

    public LiveData<Term> getTermById(String termId){
        int id = Integer.parseInt(termId);
        return plannerRepository.getTermById(id);
    }
}
