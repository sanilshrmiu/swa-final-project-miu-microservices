package com.StudentService.controller;

import com.StudentService.dto.ElementDTO;
import com.StudentService.dto.RewardDTO;
import com.StudentService.domain.Student;
import com.StudentService.dto.StudentElementDTO;
import com.StudentService.dto.StudentRewardDTO;
import com.StudentService.service.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    ElementFeignClient elementFeignClient;

    @RequestMapping(value = "/student/add",method = RequestMethod.POST)
    ResponseEntity<?> addStudent(@RequestBody Student student){
        Student response = studentService.addStudent(student);
        return new ResponseEntity<Student>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/student/update",method = RequestMethod.PUT)
    ResponseEntity<?> updateStudent(@RequestBody Student student){
        Student response = studentService.updateStudent(student);
        return new ResponseEntity<Student>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/student/redeemReward",method = RequestMethod.PUT)
    ResponseEntity<?> getStudentReward(@RequestBody StudentRewardDTO studentReward){
        Student student = studentService.getStudentById(studentReward.getStudentId());
        List<Long> reward = new ArrayList<>();
        reward.add(studentReward.getRewardId());
        student.setRewardId(reward);
        RewardDTO rewardData =  rewardFeignClient.getRewardData(student.getRewardId().get(0));
        if(student.getScore()>=rewardData.getPrice()) {
            if(rewardData.getRewardType().matches("ELEMENT")) {
                Boolean avatarCheck = avatarFeignClient.avatarUpdate(student.getAvatarId(), rewardData.getRewardTypeId());
            }
            double newScore = student.getScore() - rewardData.getPrice();
            student.setScore(newScore);
            Student response = studentService.updateStudent(student);
            return new ResponseEntity<Student>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Not sufficient score to get reward", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/student/purchseElement",method = RequestMethod.PUT)
    ResponseEntity<?> getPurchaseElement(@RequestBody StudentElementDTO studentElement){
        Student student = studentService.getStudentById(studentElement.getStudentId());
        ElementDTO elementDTO =  elementFeignClient.getElementDataById(studentElement.getElementId());
        if(student.getScore()>=elementDTO.getPrice()) {
            Boolean avatarCheck = avatarFeignClient.avatarUpdate(student.getAvatarId(), studentElement.getElementId());
            double newScore = student.getScore() - elementDTO.getPrice();
            student.setScore(newScore);
            Student response = studentService.updateStudent(student);
            return new ResponseEntity<Student>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Not sufficient score to purchase Element", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/student/removeElement",method = RequestMethod.PUT)
    ResponseEntity<?> getRemoveElement(@RequestBody StudentElementDTO studentElement){
        Student student = studentService.getStudentById(studentElement.getStudentId());
        ElementDTO elementDTO =  elementFeignClient.getElementDataById(studentElement.getElementId());
       // Boolean avatarCheck = avatarFeignClient.avatarUpdate(student.getAvatarId(), studentElement.getElementId());
        double newScore = student.getScore() + elementDTO.getPrice();
        student.setScore(newScore);
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
        @RequestMapping(value = "/avatars/addElement/{avatarId}/{elementId}",method = RequestMethod.PUT)
        public Boolean avatarUpdate(@PathVariable("avatarId") Long rewardId, @PathVariable("elementId") Long elementId);
    }

    @FeignClient(name = "ElementService")
    interface ElementFeignClient{
        @RequestMapping(value ="/elements/{elementId}", method = RequestMethod.GET)
        public ElementDTO getElementDataById(@PathVariable("elementId") Long elementId);
    }

    @FeignClient(name = "RewardService")
    interface RewardFeignClient{
        @RequestMapping(value ="/rewards/get/{rewardId}", method = RequestMethod.GET)
        public RewardDTO getRewardData(@PathVariable("rewardId") Long rewardId);

        @RequestMapping(value ="/reward", method = RequestMethod.GET)
        public Boolean getRewardId();
    }

}
