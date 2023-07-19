package com.RewardService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
    private Long id;
    private String elementId;
    private String name;
    private int quantity;
    private RewardType rewardType;
    private Long rewardTypeId;
    private double price;
}
