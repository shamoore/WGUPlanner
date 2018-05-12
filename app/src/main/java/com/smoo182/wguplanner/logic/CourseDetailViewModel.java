package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.data.datatypes.MentorAssignment;
import com.smoo182.wguplanner.data.datatypes.MentorCourses;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CourseDetailViewModel extends ViewModel {
    private PlannerRepository plannerRepository;

    @Inject
    CourseDetailViewModel(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    public void addCourse(Course course) { new AddCourseTask().execute(course); }

    public void deleteCourse(Course course) { new DeleteCourseTask().execute(course); }

    public LiveData<Course> getCourseByCode(String courseCode) {
        return plannerRepository.getCourseByCode(courseCode);
    }

    public LiveData<List<Assessment>> getAssessmentsByCourse(String courseCodeExtra) {
        return plannerRepository.getAssessmentsByCourse(courseCodeExtra);
    }

    public void assignMentorToCourse(MentorAssignment mentorAssignment) {
        MentorCourses mentorCourse = new MentorCourses(mentorAssignment.getCourseCode(), mentorAssignment.getName());
                new AssignMentorToCourseTask().execute(mentorCourse);
    }

    public void unAssignMentorFromCourse(MentorAssignment mentorAssignment) {
        MentorCourses mentorCourse = new MentorCourses(mentorAssignment.getCourseCode(), mentorAssignment.getName());
            new UnAssignMentorFromCourseTask().execute(mentorCourse);
    }


    public LiveData<List<MentorAssignment>> getMentorsByCourse(String courseCodeExtra) {
        return plannerRepository.getMentorsByCourse(courseCodeExtra);
    }


    public LiveData<List<Mentor>> getMentors(){
        return plannerRepository.getListofMentors();
    }


    private class AddCourseTask extends AsyncTask<Course, Void, Void> {

        @Override
        protected Void doInBackground(Course... courses) {
            plannerRepository.createNewCourse(courses[0]);
            return null;
        }
    }

    private class DeleteCourseTask extends  AsyncTask<Course, Void, Void> {

        @Override
        protected Void doInBackground(Course... courses) {
            plannerRepository.deleteCourse(courses[0]);
            return null;
        }
    }

    private class AssignMentorToCourseTask extends  AsyncTask<MentorCourses, Void, Void> {

        @Override
        protected Void doInBackground(MentorCourses... mentorCourses) {
            plannerRepository.assignMentorToCourse(mentorCourses[0]);
            return null;
        }
    }

    private class UnAssignMentorFromCourseTask extends  AsyncTask<MentorCourses, Void, Void> {

        @Override
        protected Void doInBackground(MentorCourses... mentorCourses) {
            plannerRepository.unAssignMentorFromCourse(mentorCourses[0]);
            return null;
        }
    }
}
