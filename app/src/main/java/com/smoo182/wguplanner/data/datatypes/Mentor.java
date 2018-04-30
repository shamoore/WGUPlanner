package com.smoo182.wguplanner.data.datatypes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity
public class Mentor {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    Integer id;

    @ColumnInfo(name = "name")
    @NonNull
    String name;

    @ColumnInfo(name ="email")
    @NonNull
    String email;

    @ColumnInfo(name ="phone)")
    @NonNull
    String phone;

    ArrayList<Course> mentors;

    public Mentor(Integer id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.mentors = new ArrayList<>();
    }

    public Integer getId() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Course> getMentors() {
        return mentors;
    }

    public void setMentors(ArrayList<Course> mentors) {
        this.mentors = mentors;
    }
}
