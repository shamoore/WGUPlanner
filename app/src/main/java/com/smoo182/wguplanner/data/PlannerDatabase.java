package com.smoo182.wguplanner.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.data.datatypes.MentorCourses;
import com.smoo182.wguplanner.data.datatypes.Note;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.data.datatypes.Term;

@Database(entities = { Assessment.class, Course.class, Mentor.class, Note.class, Quote.class, Term.class, MentorCourses.class},
        version = 1, exportSchema = false)
public abstract  class PlannerDatabase extends RoomDatabase {
    public abstract PlannerDao plannerDao();
}
