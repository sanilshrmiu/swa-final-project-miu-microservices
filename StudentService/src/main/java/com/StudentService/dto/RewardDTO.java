package com.StudentService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardDTO {
    private Long id;
    private String elementId;
    private String name;
    private int quantity;
    private String rewardType;
    private Long rewardTypeId;
    private double price;
}
