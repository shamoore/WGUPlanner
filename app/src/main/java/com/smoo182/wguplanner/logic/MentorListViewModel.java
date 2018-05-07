package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Mentor;

import java.util.List;

import javax.inject.Inject;

public class MentorListViewModel extends ViewModel {
    private PlannerRepository plannerRepository;

    @Inject
    public MentorListViewModel(PlannerRepository plannerRepository){
        this.plannerRepository = plannerRepository;
    }

    public LiveData<List<Mentor>> getListOfMentors() { return this.plannerRepository.getListofMentors(); }
}
