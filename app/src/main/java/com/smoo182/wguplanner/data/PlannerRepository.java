package com.smoo182.wguplanner.data;

import android.arch.lifecycle.LiveData;

import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.data.datatypes.MentorAssignment;
import com.smoo182.wguplanner.data.datatypes.MentorCourses;
import com.smoo182.wguplanner.data.datatypes.Reminder;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.data.datatypes.Term;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class PlannerRepository {

    private final PlannerDao plannerDao;

    @Inject
    public PlannerRepository(PlannerDao plannerDao){
        this.plannerDao = plannerDao;
    }


// Get Lists
    public LiveData<List<Term>> getListOfTerms(){
        return plannerDao.getTermList();
    }
    public LiveData<List<Course>> getListofCourses(){
        return plannerDao.getCourseList();
    }
    public LiveData<List<Assessment>> getListofAssessments(){
        return plannerDao.getAssessmentList();
    }
    public LiveData<List<Mentor>> getListofMentors(){
        return plannerDao.getMentorList();
    }

//Get Filtered Lists

    public LiveData<List<Course>> getCoursesByTerm(String termTitle){
        return plannerDao.getCoursesByTerm(termTitle);
    }

    public LiveData<List<Course>> getCoursesByMentor(String mentorName){
        return plannerDao.getCoursesByMentor(mentorName);
    }
    public LiveData<List<Assessment>> getAssessmentsByCourse(String courseCode){
        return plannerDao.getAssessmentsByCourse(courseCode);
    }

    public LiveData<List<Course>> getCoursesByAssignedMentor(String mentorNameExtra) {
        return plannerDao.getCoursesByAssignedMentor(mentorNameExtra);
    }

    public LiveData<List<MentorAssignment>> getMentorsByCourse(String courseCode){
             return plannerDao.getMentorsByCourse(courseCode);
    }


//Inserts

    public void createNewTerm(Term term){
        plannerDao.insertTerm(term);
    }

    public void createNewCourse(Course course){ plannerDao.insertCourse(course); }

    public void createNewAssessment(Assessment assessment){ plannerDao.insertAssessment(assessment); }

    public void createNewMentor(Mentor mentor){
        plannerDao.insertMentor(mentor);
    }

    public void createNewReminder(Reminder reminder){
        plannerDao.insertReminder(reminder);
    }

//Deletes

    public void deleteTerm(Term term){
        plannerDao.deleteTerm(term);
    }

    public void deleteCourse(Course course){
        plannerDao.deleteCourse(course);
    }

    public void deleteAssessment(Assessment assessment){
        plannerDao.deleteAssessment(assessment);
    }

    public void deleteMentor(Mentor mentor){
        plannerDao.deleteMentor(mentor);
    }

    public void deleteNote(Reminder reminder){
        plannerDao.deleteReminder(reminder);
    }


//Get Individuals

    public LiveData<Quote> getRandomQuote(){ return plannerDao.getRandomQuote(); }

    public LiveData<Course> getCourseByCode(String code){ return plannerDao.getCourseByCode(code); }

    public LiveData<Mentor> getMentorByName(String name){
        return plannerDao.getMentorByName(name);
    }

    public LiveData<Assessment> getAssessmentByName(String name){
       return plannerDao.getAssesmentByName(name);
    }

    public LiveData<Term> getTermByTitle(String termTitleExtra) { return plannerDao.getTermByTitle(termTitleExtra); }

    public void createNewQuote(Quote quote) { plannerDao.insertQuote(quote); }

    public void assignMentorToCourse(MentorCourses mentorCourse) {
        plannerDao.insertMentorCourses(mentorCourse);
    }

    public void unAssignMentorFromCourse(MentorCourses mentorCourse) {
        plannerDao.deleteMentorCourses(mentorCourse);
    }


    public LiveData<String[]> getCourseCodes() {
        return plannerDao.getCourseCodes();
    }

    public void addAssessmentReminder(Assessment assessment) {
        Reminder reminder = new Reminder(assessment.getName(), assessment.getGoalDate(), "Assessment");
        createNewReminder(reminder);
    }

    public void deleteAssessmentReminder(Assessment assessment) {
        Reminder reminder = new Reminder(assessment.getName(), assessment.getGoalDate(), "Assessment");
        deleteReminder(reminder);
    }

    public LiveData<List<Reminder>> getTodaysReminders() {

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);


       String todaysDate = dateFormat.format(calendar.getTime());
       return plannerDao.getTodaysReminders(todaysDate);

    }

    public void deleteReminder(Reminder reminder) {
        plannerDao.deleteReminder(reminder);
    }

    public void deleteCourseEndReminder(Course cours) {
        Reminder reminder = new Reminder(cours.getCode() +" " + cours.getName(), cours.getEndDate(), "Course Ends ");
        deleteReminder(reminder);

    }

    public void deleteCourseStartReminder(Course cours) {
        Reminder reminder = new Reminder(cours.getCode()+" " + cours.getName(), cours.getStartDate(), "Course Starts ");
        deleteReminder(reminder);

    }

    public void addCourseStartReminder(Course cours) {
        Reminder reminder = new Reminder(cours.getCode()+" " + cours.getName(), cours.getStartDate(), "Course Starts ");
        createNewReminder(reminder);
    }

    public void addCourseEndReminder(Course cours) {
        Reminder reminder = new Reminder(cours.getCode() +" " + cours.getName(), cours.getEndDate(), "Course Ends ");
        createNewReminder(reminder);
    }
}
