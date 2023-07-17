package com.TeacherService.domain;

public class TeachingClass {

    private int year;
    private String group;

    public TeachingClass(int year, String group) {
        this.year = year;
        this.group = group;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
