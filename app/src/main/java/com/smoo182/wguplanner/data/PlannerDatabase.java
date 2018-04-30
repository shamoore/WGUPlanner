package com.smoo182.wguplanner.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomDatabase.Builder;
import android.arch.persistence.room.TypeConverters;

import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.data.datatypes.Note;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.data.datatypes.Term;

@android.arch.persistence.room.Database(entities = {Term.class, Course.class, Assessment.class,
        Mentor.class, Note.class, Quote.class}, version =1)
public abstract class PlannerDatabase extends RoomDatabase {
    public abstract DataSourceInterface plannerDAO();
}