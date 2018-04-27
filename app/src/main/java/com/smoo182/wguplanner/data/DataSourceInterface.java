package com.smoo182.wguplanner.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.data.datatypes.Note;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.data.datatypes.Term;

import java.text.ParseException;
import java.util.List;
@Dao
public interface DataSourceInterface {

    @Query("SELECT * from Term")
    List<Term> getTermList() throws ParseException;

    @Insert
    Term addNewTerm();

    @Delete
    void deleteTerm();

    @Query("SELECT * from Course")
    List<Course> getCourseList();

    @Insert
    Course addNewCourse();

    @Delete
    void deleteCourse();

    @Query("SELECT * From Assessment")
    List<Assessment> getAssessmentList();

    @Insert
    Assessment addNewAssessment();

    @Delete
    void deleteAssessment();

    @Query("SELECT * from Mentor")
    List<Mentor> getMentorList();

    @Insert
    Mentor addNewMentor();

    @Delete
    void deleteMentor();

    @Query("SELECT * from Note")
    List<Note> getNoteList();

    @Insert
    Note addNewNote();

    @Delete
    void deleteNote();

    @Query("SELECT * from Quote")
    List<Quote> getQuoteList();


    @Query("SELECT  * from Quote ORDER BY RANDOM() LIMIT 1")
    Quote getRandomQuote();

}
