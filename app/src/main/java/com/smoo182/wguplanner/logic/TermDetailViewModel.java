package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Term;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class TermDetailViewModel extends ViewModel {

    private PlannerRepository plannerRepository;


    @Inject
    TermDetailViewModel(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    public void addTerm(Term term) {
        new AddTermTask().execute(term);
    }

    public void deleteTerm(Term term) {
        new DeleteTermTask().execute(term);
    }

    public LiveData<Term> getTermByTitle(String termTitleExtra) {
        return plannerRepository.getTermByTitle(termTitleExtra);
    }

    public LiveData<List<Course>> getCoursesByTerm(String termTitleExtra) {
        return plannerRepository.getCoursesByTerm(termTitleExtra);
    }

    public void addCourse(Course course) {
        new AddCourseTask().execute(course);
    }


    private class AddTermTask extends AsyncTask<Term, Void, Void> {
        @Override
        protected Void doInBackground(Term... terms) {
            plannerRepository.createNewTerm(terms[0]);
            return null;
        }
    }

    private class DeleteTermTask extends AsyncTask<Term, Void, Void> {

        @Override
        protected Void doInBackground(Term... terms) {
            plannerRepository.deleteTerm(terms[0]);
            return null;
        }
    }

    private class AddCourseTask extends AsyncTask<Course, Void, Void> {

        @Override
        protected Void doInBackground(Course... courses) {
            plannerRepository.createNewCourse(courses[0]);
            return null;
        }
    }
}
