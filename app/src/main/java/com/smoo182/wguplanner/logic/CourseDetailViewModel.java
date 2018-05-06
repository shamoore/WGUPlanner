package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;

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

    public void assignMentorToCourse(Mentor listMentor, String courseCodeExtra) {
        plannerRepository.assignMentorToCourse(listMentor, courseCodeExtra);
    }

    public void unAssignMentorFromCourse(Mentor listMentor, String courseCodeExtra) {
        plannerRepository.unAssignMentorFromCourse(listMentor, courseCodeExtra );
    }

    public boolean IsMentorAssigned(Mentor mentor, String courseCode) {
        return plannerRepository.isMentorAssigned(mentor, courseCode);
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
}
