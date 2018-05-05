package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Course.class, parentColumns = "code", childColumns = "courseCode", onDelete = CASCADE))
public class Assessment {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private Boolean type;
    private String status;
    private String courseCode;

    public Assessment(String name, Boolean type, String status, String courseCode) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.courseCode = courseCode;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
