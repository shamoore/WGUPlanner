package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Assessment;

import javax.inject.Inject;

public class AssessmentDetailViewModel extends ViewModel {
 private PlannerRepository plannerRepository;

 @Inject
    AssessmentDetailViewModel(PlannerRepository plannerRepository) {
     this.plannerRepository = plannerRepository;
 }

 public void addAssessment(Assessment assessment) { new AddAssessmentTask().execute(assessment); }
 public void deleteAssessment(Assessment assessment) { new DeleteAssessmentTask().execute(assessment);}

 public LiveData<Assessment> getAssessmentByTitle(String assessmentTitleExtra){
     return plannerRepository.getAssessmentByName(assessmentTitleExtra);
 }

    public LiveData<String[]> getCourseCodes() {
     return plannerRepository.getCourseCodes();
    }

    private class AddAssessmentTask extends AsyncTask<Assessment, Void, Void>{

     @Override
     protected Void doInBackground(Assessment... assessments) {
         plannerRepository.createNewAssessment(assessments[0]);
         return null;
     }
 }

 private class DeleteAssessmentTask extends AsyncTask<Assessment, Void, Void>{

     @Override
     protected Void doInBackground(Assessment... assessments) {
         plannerRepository.deleteAssessment(assessments[0]);
         return null;
     }
 }



}

