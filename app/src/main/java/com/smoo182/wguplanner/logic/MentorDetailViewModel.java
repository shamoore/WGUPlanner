package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;

import java.util.List;

import javax.inject.Inject;

public class MentorDetailViewModel extends ViewModel {

    private PlannerRepository plannerRepository;

    @Inject
    public MentorDetailViewModel(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    public void addMentor(Mentor mentor) { new AddMentorTask().execute(mentor); }

    public void deleteMentor(Mentor mentor) { new DeleteMentorTask().execute(mentor); }

    public LiveData<Mentor> getMentorByName(String mentorNameExtra) {
        return this.plannerRepository.getMentorByName(mentorNameExtra);
    }

    public LiveData<List<Course>> getCoursesByMentor(String mentorNameExtra) {
        return this.plannerRepository.getCoursesByMentor(mentorNameExtra);
    }


    private class AddMentorTask extends AsyncTask<Mentor, Void, Void> {
        @Override
        protected Void doInBackground(Mentor... mentors) {
            plannerRepository.createNewMentor(mentors[0]);
            return null;
        }
    }

    private class DeleteMentorTask extends  AsyncTask<Mentor, Void, Void> {

        @Override
        protected Void doInBackground(Mentor... mentors) {
            plannerRepository.deleteMentor(mentors[0]);
            return null;
        }
    }
}
