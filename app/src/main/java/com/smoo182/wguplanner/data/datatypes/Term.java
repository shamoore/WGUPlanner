package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity
public class Term {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private Integer id;

    @ColumnInfo(name = "title")
    @NonNull
    private String title;

    @ColumnInfo(name = "startDate")
    @NonNull
    private String startDate;

    @ColumnInfo(name = "stopDate")
    @NonNull
    private String stopDate;


    @ColumnInfo(name = "description")
    private String description;

    private ArrayList<Course> courses;

    public Term(String title, String startDate, String stopDate, String description) {

        this.title = title;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.description = description;
        this.courses = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }
}
