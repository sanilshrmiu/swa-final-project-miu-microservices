package com.StudentService.service;

import com.StudentService.domain.Student;
import com.StudentService.domain.StudentClass;
import com.StudentService.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements IStudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudent(){
        List<Student> students = studentRepository.findAll();
        if(students.size()>0){
            return students;
        }else{
            return null;
        }
    }

    public Student getStudentById(Long studentId){
        Optional<Student> students = studentRepository.findById(studentId);
        if(students!=null) return students.get();
        else return null;
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student){
        Optional<Student> student1 = studentRepository.findById(student.getStudentId());
        if(student1.isPresent()){
            StudentClass studentClass = new StudentClass(student.getStudentClass().getYear(),student.getStudentClass().getGroup());
            student.setStudentClass(studentClass);
            //student.setRewardId(student1.get().getRewardId());
            studentRepository.save(student);
            return student;
        }else{
            return null;
        }
    }

    public String deleteStudent(Long studentId){
        Optional<Student> teacher1 = studentRepository.findById(studentId);
        if(teacher1.isPresent()){
            studentRepository.deleteById(studentId);
            return "Student is successfully deleted";
        }else{
            return "Student does not exist";
        }
    }

}
