package com.TeacherService.repository;

import com.TeacherService.domain.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, Long> {


}
