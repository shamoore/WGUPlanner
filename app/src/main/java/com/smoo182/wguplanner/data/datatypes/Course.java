package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Course {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    Integer id;

    @ColumnInfo(name = "code")
    @NonNull
    String code;

    @ColumnInfo(name = "name")
    @NonNull
    String name;

    @ColumnInfo(name = "description")
    @NonNull
    String description;

    @ColumnInfo(name = "startDate")
    @NonNull
    Date startDate;

    @ColumnInfo(name = "stopDate")
    @NonNull
    Date stopDate;

    @ColumnInfo(name = "termId")
    int termId;

    ArrayList<Assessment> assessments;

    public Course(Integer id, String code, String name, String description, Date startDate, Date stopDate, int termId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.termId = termId;
        this.assessments = new ArrayList<Assessment>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(ArrayList<Assessment> assessments) {
        this.assessments = assessments;

    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }
}
