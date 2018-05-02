package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Term;

import java.util.List;

public class TermListViewModel extends ViewModel {

    private PlannerRepository plannerRepository;

    TermListViewModel(PlannerRepository plannerRepository){
        this.plannerRepository = plannerRepository;
    }

    public LiveData<List<Term>> getListOfTerms(){
        this.plannerRepository.getListofTerms();
    }

}
