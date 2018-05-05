package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.SET_NULL;

@Entity (foreignKeys = @ForeignKey(entity = Term.class, parentColumns = "title", childColumns = "termTitle", onDelete = SET_NULL))
public class Course {
    @PrimaryKey
    @NonNull
    private String code;
    private String name;
    private String note;
    private String startDate;
    private String endDate;
    private String termTitle;

    public Course(String code, String name, String note, String startDate, String endDate) {

        this.code = code;
        this.name = name;
        this.note = note;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTermTitle() { return termTitle; }

    public void setTermTitle(String termTitle) { this.termTitle = termTitle; }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

