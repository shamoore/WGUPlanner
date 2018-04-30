package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity
public class Term {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private Integer id;
    @NonNull
    private String title;
    @NonNull
    private String startDate;
    @NonNull
    private String endDate;
    private String description;
    private ArrayList<Course> courses;

    public Term(String title, String startDate, String endDate, String description) {

        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
