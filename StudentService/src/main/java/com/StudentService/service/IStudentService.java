package com.StudentService.service;

import com.StudentService.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudentService {

    public List<Student> getAllStudent();
    public Student getStudentById(Long studentId);
    public Student addStudent(Student student);
    public Student updateStudent(Student student);
    public String deleteStudent(Long studentId);

}
