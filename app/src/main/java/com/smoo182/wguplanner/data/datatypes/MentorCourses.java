package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity( primaryKeys = {"courseCode", "mentorName"})
public class MentorCourses {
    @NonNull
    private String courseCode;
    @NonNull
    private String mentorName;




    public MentorCourses(String courseCode, String mentorName) {
        this.courseCode = courseCode;
        this.mentorName = mentorName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }
}
