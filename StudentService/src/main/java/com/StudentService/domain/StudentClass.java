package com.StudentService.domain;

import lombok.Data;

@Data
public class StudentClass {
    private int year;
    private String group;

    public StudentClass(int year, String group) {
        this.year = year;
        this.group = group;
    }

}
