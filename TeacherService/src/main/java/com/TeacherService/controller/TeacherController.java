package com.TeacherService.controller;

import com.TeacherService.domain.Teacher;
import com.TeacherService.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    SchoolFeignClient schoolFeignClient;

    @PostMapping("/teacher/add")
    ResponseEntity<?> addTeacher(@RequestBody Teacher teacher){
        Boolean schoolCheck =  schoolFeignClient.getSchoolId(teacher.getSchoolId());
        if(!schoolCheck) {
            return new ResponseEntity<String>("School Id does not exist", HttpStatus.OK);
        }
        Teacher response = teacherService.addTeacher(teacher);
        return new ResponseEntity<Teacher>(response, HttpStatus.OK);
    }

    @PutMapping("/teacher/update")
    ResponseEntity<?> updateTeacher(@RequestBody Teacher teacher){
        Teacher response = teacherService.updateTeacher(teacher);
        return new ResponseEntity<Teacher>(response, HttpStatus.OK);
    }

    @DeleteMapping("/teacher/delete/{teacherId}")
    ResponseEntity<String> deleteTeacher(@PathVariable Long teacherId){
        String response = teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/teacher/get/{teacherId}")
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

    @GetMapping("/teacher/view")
    ResponseEntity<List<Teacher>> getAllTeacher(){
        List<Teacher> response = teacherService.getAllTeacher();
        if(response!=null) return new ResponseEntity<List<Teacher>>(response, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    @FeignClient(name = "SchoolService")
    interface SchoolFeignClient{
        @RequestMapping("/school/verifyReference/{schoolId}")
        public Boolean getSchoolId(@PathVariable("schoolId") Long schoolId);
    }

}
