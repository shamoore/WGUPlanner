package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static android.arch.persistence.room.ForeignKey.NO_ACTION;
import static android.arch.persistence.room.ForeignKey.SET_NULL;

@Entity(foreignKeys = @ForeignKey(entity = Course.class, parentColumns = "code", childColumns = "courseCode", onDelete = NO_ACTION))
public class Assessment {
    @PrimaryKey
    @NonNull
    private String name;
    private Boolean type; ///true for pA false for oA
    private String status;
    private String courseCode;
    private String goalDate;
    private boolean reminderSet;

    public Assessment(String name, Boolean type, String status, String courseCode, String goalDate, boolean reminderSet) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.courseCode = courseCode;
        this.goalDate = goalDate;
        this.reminderSet = reminderSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getStatus() { return status; }

    public void setStatus(String status){ this.status = status; }

    public String getCourseCode(){ return this.courseCode;}

    public void setCourseCode(String courseCode){ this.courseCode = courseCode; }

    public String getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(String goalDate) {
        this.goalDate = goalDate;
    }

    public boolean isReminderSet() {
        return reminderSet;
    }

    public void setReminderSet(boolean reminderSet) {
        this.reminderSet = reminderSet;
    }
}
