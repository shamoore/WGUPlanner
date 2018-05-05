package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Course;

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
