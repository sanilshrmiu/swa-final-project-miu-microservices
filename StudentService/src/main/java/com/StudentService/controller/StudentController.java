package com.StudentService.controller;

import com.StudentService.domain.Student;
import com.StudentService.service.StudentServiceImp;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentServiceImp studentService;

    @Autowired
    SchoolFeignClient schoolFeignClient;
    @Autowired
    AvatarFeignClient avatarFeignClient;
    @Autowired
    RewardFeignClient rewardFeignClient;

    @RequestMapping(value = "/student/add",method = RequestMethod.POST)
    ResponseEntity<?> addStudent(@RequestBody Student student){

       // return new ResponseEntity<String>("School Id does not exist", HttpStatus.OK);

        Boolean schoolCheck =  schoolFeignClient.getSchoolId(student.getSchoolId());
        Boolean avatarCheck =  avatarFeignClient.verifyReference(student.getAvatarId());
        Boolean rewardCheck =  rewardFeignClient.getRewardId(student.getRewardId());
        if(!schoolCheck) {
            return new ResponseEntity<String>("School Id does not exist", HttpStatus.OK);
        }else if(!avatarCheck){
            return new ResponseEntity<String>("Avatar Id does not exist", HttpStatus.OK);
        }else if(!rewardCheck){
            return new ResponseEntity<String>("Reward Id does not exist", HttpStatus.OK);
        }
        Student response = studentService.addStudent(student);
        return new ResponseEntity<Student>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/student/update",method = RequestMethod.PUT)
    ResponseEntity<?> updateStudent(@RequestBody Student student){
        Boolean schoolCheck =  schoolFeignClient.getSchoolId(student.getSchoolId());
        Boolean avatarCheck =  avatarFeignClient.verifyReference(student.getAvatarId());
        Boolean rewardCheck =  rewardFeignClient.getRewardId(student.getRewardId());
        if(!schoolCheck) {
            return new ResponseEntity<String>("School Id does not exist", HttpStatus.OK);
        }else if(!avatarCheck){
            return new ResponseEntity<String>("Avatar Id does not exist", HttpStatus.OK);
        }else if(!rewardCheck){
            return new ResponseEntity<String>("Reward Id does not exist", HttpStatus.OK);
        }
        Student response = studentService.updateStudent(student);
        return new ResponseEntity<Student>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/student/delete/{studentId}",method = RequestMethod.DELETE)
    ResponseEntity<String> deleteTeacher(@PathVariable Long studentId){
        String response = studentService.deleteStudent(studentId);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/student/get/{studentId}",method = RequestMethod.GET)
    ResponseEntity<?> getStudentById(@PathVariable Long studentId){
        //return new ResponseEntity<String>("response", HttpStatus.OK);
        Student response = studentService.getStudentById(studentId);
        if(response!=null){
            return new ResponseEntity<Student>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/student/view",method = RequestMethod.GET)
    ResponseEntity<List<Student>> getAllStudent(){
        List<Student> response = studentService.getAllStudent();
        if(response!=null) return new ResponseEntity<List<Student>>(response, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @FeignClient(name = "SchoolService")
    interface SchoolFeignClient{
        @RequestMapping(value ="/school/verifyReference/{schoolId}", method = RequestMethod.GET)
        public Boolean getSchoolId(@PathVariable("schoolId") Long schoolId);
    }

    @FeignClient(name = "AvatarService")
    interface AvatarFeignClient{
        @RequestMapping(value = "/avatars/verifyReference/{avatarId}",method = RequestMethod.GET)
        public Boolean verifyReference(@PathVariable("avatarId") Long avatarId);
    }

    @FeignClient(name = "RewardService")
    interface RewardFeignClient{
        @RequestMapping(value ="/rewards/verifyReference/{rewardId}", method = RequestMethod.GET)
        public Boolean getRewardId(@PathVariable("rewardId") Long rewardId);
    }

}
