package com.TeacherService.domain;

import jakarta.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Teacher {

    @Id
    private Long teacherId;
    private String firstName;
    private String lastName;

    private Long schoolId;

    private Contact contact;
    private TeachingClass teachingClass;

    public Teacher(Long teacherId, String firstName, String lastName, Long schoolId, Contact contact, TeachingClass teachingClass) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolId = schoolId;
        this.contact = contact;
        this.teachingClass = teachingClass;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public TeachingClass getTeachingClass() {
        return teachingClass;
    }

    public void setTeachingClass(TeachingClass teachingClass) {
        this.teachingClass = teachingClass;
    }
}
