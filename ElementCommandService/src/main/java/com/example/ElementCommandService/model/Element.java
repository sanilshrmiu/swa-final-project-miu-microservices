package com.example.ElementCommandService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Element {
    @Id
    private String id;

    private String type;
    private double price;
}
