package com.TeacherService.service;

import com.TeacherService.domain.Contact;
import com.TeacherService.domain.Teacher;
import com.TeacherService.domain.TeachingClass;
import com.TeacherService.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher addTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher){
        Optional<Teacher> teacher1 = teacherRepository.findById(teacher.getTeacherId());
        if(teacher1.isPresent()){
            Contact contact = new Contact(teacher.getContact().getEmail(), teacher.getContact().getPhoneNumber());
            teacher.setContact(contact);
            TeachingClass teachingClass = new TeachingClass(teacher.getTeachingClass().getYear(),teacher.getTeachingClass().getGroup());
            teacher.setTeachingClass(teachingClass);
            teacherRepository.save(teacher);
        }
        return teacher;
    }

    public String deleteTeacher(String teacherId){
        Optional<Teacher> teacher1 = teacherRepository.findById(teacherId);
        if(teacher1.isPresent()){
            teacherRepository.deleteById(teacherId);
            return "Teacher is successfully deleted";
        }else{
            return "Teacher does not exist";
        }
    }

    public Teacher getTeacherById(String teacherId){
        Optional<Teacher> teacher1 = teacherRepository.findById(teacherId);
       // return teacher1;
        if(teacher1.isPresent()){
            //System.out.println("Get teacher "+ teacherId);
            return teacher1.get();
        }else{
            return null;
        }
    }

    public List<Teacher> getAllTeacher(){
        List<Teacher> teacher1 = teacherRepository.findAll();
        if(teacher1.size()>0){
            return teacher1;
        }else{
            return null;
        }
    }









}
