package com.TeacherService.controller;

import com.TeacherService.domain.Teacher;
import com.TeacherService.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping("/add")
    ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        Teacher response = teacherService.addTeacher(teacher);
        return new ResponseEntity<Teacher>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<?> updateTeacher(@RequestBody Teacher teacher){
        Teacher response = teacherService.updateTeacher(teacher);
        return new ResponseEntity<Teacher>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{teacherId}")
    ResponseEntity<String> deleteTeacher(@PathVariable Long teacherId){
        String response = teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{teacherId}")
    ResponseEntity<?> getTeacherById(@PathVariable Long teacherId){
        Teacher response = teacherService.getTeacherById(teacherId);
      // return new ResponseEntity<Teacher>(response, HttpStatus.OK);
        if(response!=null){
            return new ResponseEntity<Teacher>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/view")
    ResponseEntity<List<Teacher>> getAllTeacher(){
        List<Teacher> response = teacherService.getAllTeacher();
        if(response!=null) return new ResponseEntity<List<Teacher>>(response, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
