package com.StudentService.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class KafkaMessageTemplate implements Serializable {

    private String email;
    private String createdUserType;

}

