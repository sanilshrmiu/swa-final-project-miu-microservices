package com.TeacherService.service;

import com.TeacherService.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITeacherService {
    public List<Teacher> getAllTeacher();
    public Teacher getTeacherById(Long teacherId);
    public Teacher addTeacher(Teacher teacher);
    public Teacher updateTeacher(Teacher teacher);
    public String deleteTeacher(Long teacherId);

}
