package com.smoo182.wguplanner.logic;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.smoo182.wguplanner.data.PlannerDao;
import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Term;

import java.util.List;

import javax.inject.Inject;

public class TermListViewModel extends ViewModel {

    private PlannerRepository plannerRepository;

    @Inject
    TermListViewModel(PlannerRepository plannerRepository){
        this.plannerRepository = plannerRepository;
    }


    public LiveData<List<Term>> getListOfTerms(){
        return this.plannerRepository.getListofTerms();
    }

}
