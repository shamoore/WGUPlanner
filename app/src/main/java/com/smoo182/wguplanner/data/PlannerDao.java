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
import com.smoo182.wguplanner.data.datatypes.MentorCourses;
import com.smoo182.wguplanner.data.datatypes.Note;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.data.datatypes.Term;

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

    @Query("SELECT * FROM Course where termTitle= :termTitle OR termTitle is NULL")
    LiveData<List<Course>> getCoursesByTerm(String termTitle);

    @Query("SELECT * FROM Assessment where courseCode= :courseCode OR courseCode is NULL")
    LiveData<List<Assessment>> getAssessmentsByCourse(String courseCode);

    @Query("SELECT m.name, m.email, m.phone FROM Mentor m INNER JOIN MentorCourses mc where mc.courseCode = :courseCode")
    LiveData<List<Mentor>> getMentorsByCourse(String courseCode);

    @Query("SELECT c.code, c.name, c.note, c.startDate, c.endDate, c.termTitle FROM Course c INNER JOIN MentorCourses mc where mc.mentorName = :mentorName")
    LiveData<List<Course>> getCoursesByMentor(String mentorName);

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuote(Quote quote);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMentorCourses(MentorCourses mentorCourses);


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

    @Delete
    void deleteMentorCourses(MentorCourses mentorCourses);

    //Get Individuals

    @Query("SELECT  * from Quote ORDER BY RANDOM() LIMIT 1")
    LiveData<Quote> getRandomQuote();

    @Query("SELECT * from Course where code = :code")
    LiveData<Course> getCourseByCode(String code);

    @Query("SELECT * from Mentor where name = :name")
    LiveData<Mentor> getMentorByName(String name);

    @Query("SELECT * from Assessment where id = :id")
    Assessment getAssesmentById(int id);

    @Query("SELECT * from Note where id = :id")
    Note getNoteById(int id);

    @Query("SELECT * from Term where title = :termTitleExtra")
    LiveData<Term> getTermByTitle(String termTitleExtra);



    //Exists Queries
    @Query("SELECT COUNT(*) FROM MentorCourses where mentorName = :mentorName AND courseCode = :courseCode")
    int isMentorAssigned(String mentorName, String courseCode);

}
