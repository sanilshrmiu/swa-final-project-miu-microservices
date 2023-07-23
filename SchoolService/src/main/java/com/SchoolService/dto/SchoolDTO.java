package com.SchoolService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {
    private String schoolId;
    private String Name;
    private String address;
    private ContactDTO contact;
}
