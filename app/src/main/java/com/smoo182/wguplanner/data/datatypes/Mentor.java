package com.smoo182.wguplanner.data.datatypes;

import java.util.ArrayList;

public class Mentor {
    Integer id;
    String name;
    String email;
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
