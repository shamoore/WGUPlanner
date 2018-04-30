package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MentorCourses {

    @PrimaryKey (autoGenerate = true)

    @ColumnInfo(name = "courseId")
    private  int courseId;

    @ColumnInfo(name = "mentorId")
    private int mentorId;

}
