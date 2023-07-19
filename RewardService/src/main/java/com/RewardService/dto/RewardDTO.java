package com.RewardService.dto;

import com.RewardService.domain.Reward;
import com.RewardService.domain.RewardType;
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
    private RewardType rewardType;
    private Long rewardTypeId;
    private double price;
}
