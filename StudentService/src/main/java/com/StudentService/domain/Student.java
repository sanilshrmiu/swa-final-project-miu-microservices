package com.StudentService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private Long studentId;
    private String firstName;
    private String lastName;
    private String studentNumber;
    private Long SchoolId;
    private StudentClass studentClass;
    private double score = 1000;
    private Long avatarId;
    private List<Long> rewardId;
}
