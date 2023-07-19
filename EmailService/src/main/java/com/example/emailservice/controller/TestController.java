package com.example.emailservice.controller;

import com.example.emailservice.dto.KafkaMessageTemplate;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public void test(){
        KafkaMessageTemplate kafkaMessageTemplate = new KafkaMessageTemplate();
        kafkaMessageTemplate.setEmail("sanil.shrestha.12@gmail.com");
        kafkaMessageTemplate.setCreatedUserType("Student");
        Gson gson = new Gson();
        kafkaTemplate.send("email-topic",gson.toJson(kafkaMessageTemplate));
    }

}
