package com.StudentService.controller;

import com.StudentService.domain.Student;
import com.StudentService.service.StudentService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    ResponseEntity<Student> addStudent(@RequestBody Student student){
        studentService.get
        Student response = studentService.addStudent(student);
        return new ResponseEntity<Student>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<?> updateStudent(@RequestBody Student teacher){
        Student response = studentService.updateStudent(teacher);
        return new ResponseEntity<Student>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{teacherId}")
    ResponseEntity<String> deleteTeacher(@PathVariable Long teacherId){
        String response = studentService.deleteStudent(teacherId);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{teacherId}")
    ResponseEntity<?> getTeacherById(@PathVariable Long teacherId){
        Student response = studentService.getStudentById(teacherId);
        // return new ResponseEntity<Teacher>(response, HttpStatus.OK);
        if(response!=null){
            return new ResponseEntity<Student>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/view")
    ResponseEntity<List<Student>> getAllTeacher(){
        List<Student> response = studentService.getAllStudent();
        if(response!=null) return new ResponseEntity<List<Student>>(response, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
