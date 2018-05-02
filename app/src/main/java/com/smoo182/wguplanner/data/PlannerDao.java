package com.smoo182.wguplanner.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.data.datatypes.Note;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.data.datatypes.Term;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PlannerDao {

    //Get Full Lists.
    @Query("SELECT * from Term")
    LiveData<List<Term>> getTermList();


    @Query("SELECT * from Course")
    LiveData<List<Course>> getCourseList();


    @Query("SELECT * From Assessment")
    LiveData<List<Assessment>> getAssessmentList();


    @Query("SELECT * from Mentor")
    LiveData<List<Mentor>> getMentorList();

    @Query("SELECT * from Note")
    LiveData<List<Note>> getNoteList();




    //Get Filtered Lists

    @Query("SELECT * FROM Course where termId= :termId")
    LiveData<List<Course>> getCoursesByTerm(int termId);

    @Query("SELECT * FROM Assessment where courseId= :courseId")
    LiveData<List<Assessment>> getAssessmentsByCourse(int courseId);

    @Query("SELECT m.id, m.name, m.email, m.phone FROM Mentor m INNER JOIN MentorCourses mc where mc.courseId = :courseId")
    LiveData<List<Mentor>> getMentorsByCourse(int courseId);

    @Query("SELECT c.id, c.code, c.name, c.description, c.startDate, c.endDate, c.termId FROM Course c INNER JOIN MentorCourses mc where mc.mentorId = :mentorId")
    LiveData<List<Course>> getCoursesByMentor(int mentorId);

    @Query("SELECT * FROM Note where courseId = :courseId")
    LiveData<List<Note>> getNotesByCourse(int courseId);




    //Inserts. Replacing will allow edit to use the same methods.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Term term);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourse(Course course);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMentor(Mentor mentor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAssessment(Assessment assessment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);


    //Deletes

    @Delete
    void deleteTerm(Term term);

    @Delete
    void deleteCourse(Course course);

    @Delete
    void deleteMentor(Mentor mentor);

    @Delete
    void deleteAssessment(Assessment assessment);

    @Delete
    void deleteNote(Note note);


    //Get Individuals

    @Query("SELECT  * from Quote ORDER BY RANDOM() LIMIT 1")
    Quote getRandomQuote();

    @Query("SELECT * from Term where id = :id")
    Term getTermById(int id);

    @Query("SELECT * from Course where id = :id")
    Course getCourseById(int id);

    @Query("SELECT * from Mentor where id = :id")
    Mentor getMentorById(int id);

    @Query("SELECT * from Assessment where id = :id")
    Assessment getAssesmentById(int id);

    @Query("SELECT * from Note where id = :id")
    Note getNoteById(int id);

}
