package com.smoo182.wguplanner.data;

import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.data.datatypes.Note;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.data.datatypes.Term;

import java.util.List;

public interface DataSourceInterface {

    List<Term> getTermList();
    Term addNewTerm();
    void deleteTerm();

    List<Course> getCourseList();
    Course addNewCourse();
    void deleteCourse();

    List<Assessment> getAssessmentList();
    Assessment addNewAssessment();
    void deleteAssessment();

    List<Mentor> getMentorList();
    Mentor addNewMentor();
    void deleteMentor();

    List<Note> getNoteList();
    Note addNewNote();
    void deleteNote();

    List<Quote> getQuoteList();
    Quote getRandomQuote();

}
