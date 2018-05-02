package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MentorCourses {

    @PrimaryKey(autoGenerate = true)
    int id;
    int courseId;
    int mentorId;
}
