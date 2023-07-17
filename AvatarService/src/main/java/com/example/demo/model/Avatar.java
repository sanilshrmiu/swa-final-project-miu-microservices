package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Avatar {
    @Id
    private String id;
    private String head;
    private String hair;
    private String eye;
    private String eyebrow;
    private String nose;
    private String mouth;
    private String ears;
    private String body;
    private String hat;
    private String top;
    private String topColor;
    private String hatColor;

}
