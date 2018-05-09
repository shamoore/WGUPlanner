package com.smoo182.wguplanner.data.datatypes;

public class MentorAssignment extends Mentor {
    private String courseCode;

    public MentorAssignment(String courseCode, String name, String email, String phone) {
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        this.courseCode = courseCode;

    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
