package com.StudentService.service;

import com.StudentService.domain.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
public interface IStudentService {

    public List<Student> getAllStudent();
    public Student getStudentById(Long studentId);
    public Student addStudent(Student student);
    public Student updateStudent(Student student);
    public String deleteStudent(Long studentId);

    @RequestMapping(value = "school/get/{schoolId}",method = RequestMethod.GET)
    public int getSchoolId(@PathVariable("schoolId") int schoolId);

    @RequestMapping(value = "school/get/{schoolId}",method = RequestMethod.GET)
    public int getSchoolId(@PathVariable("schoolId") int schoolId);

}
